package cn.wanghaomiao.xpath.test;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import java.util.LinkedList;

/**
 * @author 汪浩淼 [haomiaowang@sohu-inc.com et.tw@163.com]
 * @since 14-3-4 下午5:03
 */
public class QuickStartSample {
    // 1. Define BuilderState Machine Event
    enum FSMEvent {
        Beg,ToA, ToB, ToC, ToD
    }

    // 2. Define BuilderState Machine Class
    @StateMachineParameters(stateType=String.class, eventType=FSMEvent.class, contextType=Integer.class)
    static class StateMachineSample extends AbstractUntypedStateMachine {
        protected void fromAToB(String from, String to, FSMEvent event, Integer context) {
            System.out.println("Transition from '"+from+"' to '"+to+"' on event '"+event+
                    "' with context '"+context+"'.");
        }

        protected void ontoB(String from, String to, FSMEvent event, Integer context) {
            System.out.println(from+"  Entry BuilderState \'"+to+"\'."+event);
        }

        protected void start(String from,String to,FSMEvent event,Integer context){
            System.out.println("a"+from+" "+to+" "+event+" "+context);
        }
    }

    public static void main(String[] args) {
        // 3. Build BuilderState Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineSample.class);
//        builder.transit().fromAny().to("A").on(FSMEvent.Beg).callMethod("fromAnyToA");
        builder.onEntry("A").callMethod("start");
        builder.externalTransition().from("A").to("B").on(FSMEvent.ToB).callMethod("fromAToB");
        builder.onEntry("B").callMethod("ontoB");
//        builder.

        // 4. Use BuilderState Machine
        UntypedStateMachine fsm = builder.newStateMachine("A");
        fsm.fire(FSMEvent.ToB, 10);

        System.out.println("Current state is "+fsm.getCurrentState());
        Object a = new LinkedList<String>();
        System.out.println(a.getClass());
    }
}
