package cn.wanghaomiao.xpath.core;

import cn.wanghaomiao.xpath.model.Node;
import cn.wanghaomiao.xpath.model.Predicate;
import cn.wanghaomiao.xpath.util.EmMap;

import java.io.IOException;

/**
 * 用于生成xpath语法树的有限状态机
 * @author 汪浩淼 [et.tw@163.com]
 * @since 13-12-26 下午3:48
 */
public class NodeTreeBuilderStateMachine {
    BuilderState state = BuilderState.SCOPE;
    XContext context = new XContext();
    int cur=0;
    StringBuilder accum = new StringBuilder();
    enum BuilderState {
        SCOPE {
            @Override
            public void parser(NodeTreeBuilderStateMachine stateMachine, char[] xpath) {
                 while (stateMachine.cur<xpath.length){
                     if (!(xpath[stateMachine.cur]=='/'||xpath[stateMachine.cur]=='.')){
                         stateMachine.state = AXIS;
                         Node xn = new Node();
                         stateMachine.context.xpathTr.add(xn);
                         xn.setScopeEm(EmMap.getInstance().scopeEmMap.get(stateMachine.accum.toString()));
                         stateMachine.accum=new StringBuilder();
                         break;
                     }
                     stateMachine.accum.append(xpath[stateMachine.cur]);
                     stateMachine.cur+=1;
                 }
            }
        },
        AXIS {
            @Override
            public void parser(NodeTreeBuilderStateMachine stateMachine, char[] xpath) {
                int curtmp = stateMachine.cur;
                StringBuilder accumTmp=new StringBuilder();
                while (curtmp<xpath.length&&xpath[curtmp]!='['&&xpath[curtmp]!='/'){
                    if (xpath[curtmp]==':'){
                        stateMachine.context.xpathTr.getLast().setAxis(accumTmp.toString());
                        stateMachine.cur=curtmp+2;
                        stateMachine.state = TAG;
                        break;
                    }
                    accumTmp.append(xpath[curtmp]);
                    curtmp+=1;
                }
                stateMachine.state = TAG;
            }
        },
        TAG {
            @Override
            public void parser(NodeTreeBuilderStateMachine stateMachine, char[] xpath) {
                while (stateMachine.cur<xpath.length&&xpath[stateMachine.cur]!='['&&xpath[stateMachine.cur]!='/'){
                    stateMachine.accum.append(xpath[stateMachine.cur]);
                    stateMachine.cur+=1;
                }
                stateMachine.context.xpathTr.getLast().setTagName(stateMachine.accum.toString());
                stateMachine.accum = new StringBuilder();
                if (stateMachine.cur==xpath.length){
                    stateMachine.state =END;
                }else if (xpath[stateMachine.cur]=='/'){
                    stateMachine.state = SCOPE;
                }else if (xpath[stateMachine.cur]=='['){
                    stateMachine.state = PREDICATE;
                }
            }
        },
        PREDICATE{
            @Override
            public void parser(NodeTreeBuilderStateMachine stateMachine, char[] xpath) {
                int deep = 0;
                stateMachine.cur+=1;
                while (!(xpath[stateMachine.cur]==']'&&deep==0)){
                    if (xpath[stateMachine.cur]=='['){
                        deep+=1;
                    }
                    if (xpath[stateMachine.cur]==']'){
                        deep-=1;
                    }
                    stateMachine.accum.append(xpath[stateMachine.cur]);
                    stateMachine.cur+=1;
                }
                Predicate predicate = stateMachine.genPredicate(stateMachine.accum.toString());
                stateMachine.context.xpathTr.getLast().setPredicate(predicate);
                stateMachine.accum = new StringBuilder();
                if (stateMachine.cur<xpath.length-1){
                    stateMachine.cur+=1;
                    stateMachine.state = SCOPE;
                }else {
                    stateMachine.state = END;
                }
            }
        },
        END{
            @Override
            public void parser(NodeTreeBuilderStateMachine stateMachine, char[] xpath) {
            }
        };
        public void parser(NodeTreeBuilderStateMachine stateMachine,char[] xpath){}
    }
    public void process(char[] xpath){
        state.parser(this, xpath);
    }

    /**
     * 根据谓语字符串初步生成谓语体
     * @param pre
     * @return
     */
    public Predicate genPredicate(String pre){
        StringBuilder op = new StringBuilder();
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        Predicate predicate = new Predicate();
        char[] preArray = pre.toCharArray();
        int index = preArray.length-1;
        int argDeep = 0;
        int opFlag = 0;
        while (index>=0){
            char tmp = preArray[index];
            if (tmp=='\''){
                argDeep+=1;
            }
            if (argDeep==1&&tmp!='\''){
                right.insert(0,tmp);
            }else if (argDeep==2&&EmMap.getInstance().commOpChar.contains(tmp)){
                op.insert(0,tmp);
                opFlag=1;
            }else if (argDeep>=2&&opFlag>0){
                argDeep++;//取完操作符后剩下的都属于left
                left.insert(0,tmp);
            }
            index-=1;
        }
        predicate.setOpEm(EmMap.getInstance().opEmMap.get(op.toString()));
        predicate.setLeft(left.toString());
        predicate.setRight(right.toString());
        predicate.setValue(pre);
        return predicate;
    }


    public static void main(String[] args) throws IOException {
        String expression = ".//div[@class='aa']/child::li[contains(./text(),'abc')]/li[5]";
        NodeTreeBuilderStateMachine st = new NodeTreeBuilderStateMachine();
        while (st.state != BuilderState.END){
            st.state.parser(st, expression.toCharArray());
        }
        for (Node n:st.context.xpathTr){
            System.out.println("-------------------");
            System.out.println(n.getScopeEm().val());
            System.out.println(n.getAxis());
            System.out.println(n.getTagName());
            System.out.println(n.getPredicate().getValue());
            System.out.println(n.getPredicate().getOpEm());
            System.out.println(n.getPredicate().getLeft());
        }
    }
}
