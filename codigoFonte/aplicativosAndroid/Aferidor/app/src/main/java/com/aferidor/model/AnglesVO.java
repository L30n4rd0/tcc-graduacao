package com.aferidor.model;

import java.io.Serializable;

/**
 * Created by leonardo on 12/06/15.
 */
public class AnglesVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private float x, y;

    public AnglesVO(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
