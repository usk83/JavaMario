import java.applet.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Kuribo extends MovableGameObject {
  private static final int SPEED = 4; //スピード
  public Kuribo(float _px, float _py) {
    super(_px, _py, "res/enemy/kuribo.png", 3);
    //iconCount = 1;  //画像の何番面を表示するか
    //animWait = 50;  //アニメ用スリープ時間
    objDire = Direction.LEFT;
    vx = SPEED * objDire;
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
