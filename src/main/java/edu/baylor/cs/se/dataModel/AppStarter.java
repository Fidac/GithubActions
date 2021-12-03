package edu.baylor.cs.se.dataModel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStarter.class, args);

//        Connection connection = null;
//        try {
//            // Producer
//            ConnectionFactory connectionFactory = new ActiveMQJMSConnectionFactory(
//                    "tcp://localhost:61616");
//            connection = connectionFactory.createConnection();
//            Session session = connection.createSession(false,
//                    Session.AUTO_ACKNOWLEDGE);
//            Queue queue = session.createQueue("jms::datamodel.teamStatus.queue");
//
//            // Consumer
//            TeamStatusConsumer consumer = new TeamStatusConsumer();
//            connection.start();
//            Thread.sleep(1000000);
//            session.close();
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
////            broker.stop();
//        }
    }
}
