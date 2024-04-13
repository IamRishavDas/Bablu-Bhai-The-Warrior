package Utils;


import NewGame.Game;

public class Constants {
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public final static int IDLE = 0;
        public final static int RUNNING = 1;
        public final static int JUMPING = 2;
        public final static int FALLING = 3;
        public final static int GROUND = 4;
        public final static int HIT = 5;
        public final static int ATTACK_1 = 6;
        public final static int ATTACK_JUMP_1 = 7;
        public final static int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int playerAction){
            switch(playerAction){
                case RUNNING : return 6;
                case IDLE : return 5;
                case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 : return 3;
              
                case GROUND : return 2;
                case FALLING : return 1;
                case HIT : return 4;
                default: return 1;
            }
        }

    }

    public static class Ui{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT  = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH          = (int)(B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT         = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
        }
        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE         = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }
    }
}
