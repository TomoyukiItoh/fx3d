package fx3d;

import javafx.application.Application;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import tomojavalib.mesh.Body;
import tomojavalib.threedstage.ThreeDStage;


public class ThreeDMain extends Application {
//ステージ
public ThreeDStage stage = null;


//---メイン---
public static void main(String[] args) {  Application.launch(args); }

@Override
public void start( Stage stage ) throws Exception {
this.stage = new ThreeDStage();
this.layout();
return;
}

private void layout() {
//箱の追加
/*
Box box = new Box( 3 , 3 , 3 );
box.setDrawMode( DrawMode.LINE );
//Translate boxtranslate = new Translate(1.5,1.5,1.5);
//box.getTransforms().add( boxtranslate );
 this.stage.group.getChildren().add( box );
*/

//人体モデルの追加
Body body = new Body();
body.loadBodyObj( "D:/work/洋裁/3Dbody/test.obj" );
//body.scale(body.cpoint, 0.002);
//body.remakeMeshView();
 for( int i=0;i< body.meshview.length;i++ ){
  this.stage.group.getChildren().add( body.meshview[i] );
 }

 
 
return;
}


}
