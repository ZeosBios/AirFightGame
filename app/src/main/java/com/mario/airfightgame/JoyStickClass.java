package com.mario.airfightgame;

import com.mario.framework.Input;

public class JoyStickClass {
    public static final int STICK_NONE = 0;
    public static final int STICK_UP = 1;
    public static final int STICK_UPRIGHT = 2;
    public static final int STICK_RIGHT = 3;
    public static final int STICK_DOWNRIGHT = 4;
    public static final int STICK_DOWN = 5;
    public static final int STICK_DOWNLEFT = 6;
    public static final int STICK_LEFT = 7;
    public static final int STICK_UPLEFT = 8;

    private int STICK_ALPHA = 200;
    private int LAYOUT_ALPHA = 200;
    private int OFFSET = 0;

    private int stick_width, stick_height;

    private int position_x = 0, position_y = 0, min_distance = 0;
    private float distance = 0, angle = 0;

    private float drawPosX, drawPosY;

    private boolean touch_state = false;

    public JoyStickClass (int x, int y, int width, int height) {

        stick_width = width;
        stick_height = height;
        drawPosX = (int) (0 +(190 / 2)) - (stick_width / 2);
        drawPosY = (int) (325 + (185 / 2)) - (stick_height / 2);
    }

    public void drawPosition(Input.TouchEvent event, int x, int y, int width, int height) {
        position_x = (int) (event.x - (x +(width / 2)));
        position_y = (int) (event.y - (y + (height / 2)));
        distance = (float) Math.sqrt(Math.pow(position_x, 2) + Math.pow(position_y, 2));
        angle = (float) cal_angle(position_x, position_y);

        if((event.type == Input.TouchEvent.TOUCH_DOWN) || (event.type == Input.TouchEvent.TOUCH_DRAGGED)) {
            if(distance <= (width / 2) - OFFSET) {
                drawPosX = event.x - (stick_width / 2);
                drawPosY = event.y - (stick_height / 2);
                touch_state = true;
            }
        } else if((event.type == Input.TouchEvent.TOUCH_DOWN) || (event.type == Input.TouchEvent.TOUCH_DRAGGED) && touch_state) {
            if(distance <= (width / 2) - OFFSET) {
                drawPosX = event.x - (stick_width / 2);
                drawPosY = event.y - (stick_height / 2);
            } else if(distance > (width / 2) - OFFSET){
                float xx = (float) (Math.cos(Math.toRadians(cal_angle(position_x, position_y))) * ((width / 2) - OFFSET));
                float yy = (float) (Math.sin(Math.toRadians(cal_angle(position_x, position_y))) * ((height / 2) - OFFSET));
                xx += (width / 2);
                yy += (height / 2);
                drawPosX = xx - (stick_width / 2);
                drawPosY = yy - (stick_height / 2);
            }
        } else if(event.type == Input.TouchEvent.TOUCH_UP) {
            drawPosX = (int) (0 +(190 / 2)) - (stick_width / 2);
            drawPosY = (int) (325 + (185 / 2)) - (stick_height / 2);
            touch_state = false;
        }
    }
    public int[] getPosition() {
        if(distance > min_distance && touch_state) {
            return new int[] { position_x, position_y };
        }
        return new int[] { 0, 0 };
    }

    public int getX() {
        if(distance > min_distance && touch_state) {
            return position_x;
        }
        return 0;
    }

    public int getY() {
        if(distance > min_distance && touch_state) {
            return position_y;
        }
        return 0;
    }

    public float getAngle() {
        if(distance > min_distance && touch_state) {
            return angle;
        }
        return 0;
    }

    public float getDistance() {
        if(distance > min_distance && touch_state) {
            return distance;
        }
        return 0;
    }

    public void setMinimumDistance(int minDistance) {
        min_distance = minDistance;
    }

    public int getMinimumDistance() {
        return min_distance;
    }

    public int get8Direction() {
        if(distance > min_distance && touch_state) {
            if(angle >= 247.5 && angle < 292.5 ) {
                return STICK_UP;
            } else if(angle >= 292.5 && angle < 337.5 ) {
                return STICK_UPRIGHT;
            } else if(angle >= 337.5 || angle < 22.5 ) {
                return STICK_RIGHT;
            } else if(angle >= 22.5 && angle < 67.5 ) {
                return STICK_DOWNRIGHT;
            } else if(angle >= 67.5 && angle < 112.5 ) {
                return STICK_DOWN;
            } else if(angle >= 112.5 && angle < 157.5 ) {
                return STICK_DOWNLEFT;
            } else if(angle >= 157.5 && angle < 202.5 ) {
                return STICK_LEFT;
            } else if(angle >= 202.5 && angle < 247.5 ) {
                return STICK_UPLEFT;
            }
        } else if(distance <= min_distance && touch_state) {
            return STICK_NONE;
        }
        return 0;
    }

    public int get4Direction() {
        if(distance > min_distance && touch_state) {
            if(angle >= 225 && angle < 315 ) {
                return STICK_UP;
            } else if(angle >= 315 || angle < 45 ) {
                return STICK_RIGHT;
            } else if(angle >= 45 && angle < 135 ) {
                return STICK_DOWN;
            } else if(angle >= 135 && angle < 225 ) {
                return STICK_LEFT;
            }
        } else if(distance <= min_distance && touch_state) {
            return STICK_NONE;
        }
        return 0;
    }

    public void setOffset(int offset) {
        OFFSET = offset;
    }

    public int getOffset() {
        return OFFSET;
    }

    public int getStickAlpha() {
        return STICK_ALPHA;
    }

    public void setLayoutAlpha(int alpha) {
        LAYOUT_ALPHA = alpha;
    }

    public int getLayoutAlpha() {
        return LAYOUT_ALPHA;
    }

    public void setStickSize(int width, int height) {
        stick_width = width;
        stick_height = height;
    }

    public void setStickWidth(int width) {
        stick_width = width;
    }

    public void setStickHeight(int height) {
        stick_height = height;
    }

    public int getStickWidth() {
        return stick_width;
    }

    public int getStickHeight() {
        return stick_height;
    }

    public float getDrawPosX() { return drawPosX; }

    public float getDrawPosY() { return drawPosY; }

    private double cal_angle(float x, float y) {
        if(x >= 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x));
        else if(x < 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x < 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x >= 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 360;
        return 0;
    }
}
