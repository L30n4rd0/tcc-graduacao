package model;

/**
 * Created by leonardo on 12/06/15.
 */
public class AnglesVO {

	private float x_angle, y_angle;

    public AnglesVO(float x_angle, float y_angle) {
        this.x_angle = x_angle;
        this.y_angle = y_angle;
    }

    public float getXangle() {
        return x_angle;
    }

    public float getYangle() {
        return y_angle;
    }
    
}
