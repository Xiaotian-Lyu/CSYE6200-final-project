package cc.adventure.sprite;
import cc.forestadventure.scene.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion extends Sprite {
    private int count = 0;
    private static Image[] images = new Image[] {
           new Image("/resources/bulletFire.png"),
            //爆炸图片太小了，怎么加新的
            
    };

    public Explosion(double x, double y, GameScene gameScene) {
        super(null, x, y, 0, 0, gameScene);//初始化
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {//绘制方法 计数器
        if(count >= images.length) {
            gameScene.explosion.remove(this);
            return;
        }
        image = images[count];
        double ex = x - image.getWidth()/2;
        double ey = y - image.getHeight()/2;
        graphicsContext.drawImage(image, ex, ey);
        count ++;
    }
}
