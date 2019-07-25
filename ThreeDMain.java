package fx3d;

import javafx.application.Application;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import tomojavalib.mesh.Body;
import tomojavalib.threedstage.ThreeDStage;


public class ThreeDMain extends Application {
//�X�e�[�W
public ThreeDStage stage = null;


//---���C��---
public static void main(String[] args) {  Application.launch(args); }

@Override
public void start( Stage stage ) throws Exception {
this.stage = new ThreeDStage();
this.layout();
return;
}

private void layout() {
//���̒ǉ�
/*
Box box = new Box( 3 , 3 , 3 );
box.setDrawMode( DrawMode.LINE );
//Translate boxtranslate = new Translate(1.5,1.5,1.5);
//box.getTransforms().add( boxtranslate );
 this.stage.group.getChildren().add( box );
*/

//�l�̃��f���̒ǉ�
Body body = new Body();
body.loadBodyObj( "D:/work/�m��/3Dbody/test.obj" );
//body.scale(body.cpoint, 0.002);
//body.remakeMeshView();
 for( int i=0;i< body.meshview.length;i++ ){
  this.stage.group.getChildren().add( body.meshview[i] );
 }

 
 
return;
}


}
