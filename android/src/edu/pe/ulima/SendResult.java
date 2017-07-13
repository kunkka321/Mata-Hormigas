package edu.pe.ulima;

import edu.pe.ulima.inerface.FireBaseRealTime;


public class SendResult implements FireBaseRealTime {

    private final FirebaseDataBase serviceF ;

    public SendResult() {
        serviceF = new FirebaseDataBase();
    }

    @Override
    public void submitScore(String user, int score) {
        serviceF.submitScore(user , score);
    }
}
