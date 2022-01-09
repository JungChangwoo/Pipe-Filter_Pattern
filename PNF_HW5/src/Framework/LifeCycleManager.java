/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SourceFilter("Courses.txt");
            CommonFilter filter3 = new SinkFilter("Output1.txt");
            CommonFilter filter4 = new SinkFilter("Output2.txt");
            CommonFilter filter5 = new MiddleFilter();
            
            filter5.makeInputPipes(2);
            filter1.connectOutputTo(filter5, 0);
            filter2.connectOutputTo(filter5, 1);
            filter5.makeOutputPipes(2);
            filter3.connectInputTo(filter5, 0);
            filter4.connectInputTo(filter5, 1);
            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            Thread thread4 = new Thread(filter4);
            Thread thread5 = new Thread(filter5);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
