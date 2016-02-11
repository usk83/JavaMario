import java.applet.*;

public class Kuribo extends MovableGameObject {
  private static final int SPEED = 4; //スピード

  private AudioClip kuriboSound;

  public Kuribo(float _px, float _py) {
    super(_px, _py, "res/img/kuribo.png", 3);
    objDire = Direction.LEFT;
    vx = SPEED * objDire;
    kuriboSound = Applet.newAudioClip(getClass().getClassLoader().getResource("res/sound/effects/squish.wav"));
  }

  public void playKuriboSound() {
      kuriboSound.play();
  }

  public void move() {
      super.move();
      if (vx == 0) {
          turn();
          vx = SPEED * objDire;
      }
  }

  private void turn() {
      if (objDire == Direction.LEFT) {
          objDire = Direction.RIGHT;
      }
      else if (objDire == Direction.RIGHT) {
          objDire = Direction.LEFT;
      }
  }

  //アニメの表示の管理
  protected void runAnimation() {
      iconCount += 1;
      if (iconCount >= 2) {
          iconCount = 0;
      }
  }
}
