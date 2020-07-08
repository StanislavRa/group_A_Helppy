package com.sda.controller.utilities;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    private final TranslateTransition translateTransition;

    public Animation(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(70), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }

    public void playAnim() {
        translateTransition.playFromStart();
    }
}
