package fx3d;

import javafx.application.Application;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import tomojavalib.mesh.Body;
import tomojavalib.mesh.ThreeDimension;
import tomojavalib.threedstage.ThreeDStage;

/**Obj形式のファイルを読み込んで表示する*/
public class StageSquareTriangle extends Application {
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

Box box = new Box( 3 , 3 , 3 );
box.setDrawMode( DrawMode.LINE );
//Translate boxtranslate = new Translate(1.5,1.5,1.5);
//box.getTransforms().add( boxtranslate );
 this.stage.group.getChildren().add( box );

//メッシュの追加
        TriangleMesh    mesh        = new TriangleMesh();
        float[]         points      = { 1      ,1      ,-10      ,   // p0
                                		        0      , 1      ,2      ,   // p1
                                		         1       ,0      ,1 };//     ,   // p2
  //                                   2       ,-2     ,0   };    // p3
        float[]         texCoords   = { 0 , 0 };
        int[]           faces       = { 0 , 0 , 1 , 0 , 2 , 0};//,
                               //         2 , 0 , 3 , 0 , 0 , 0 };
        mesh.getPoints().addAll( points );
        mesh.getTexCoords().addAll( texCoords );
        mesh.getFaces().addAll( faces );
       MeshView        meshView1    = new MeshView();
       meshView1.setCullFace(CullFace.NONE);//BACKで裏面非描画
        meshView1.setMesh( mesh );
 this.stage.group.getChildren().add( meshView1 );
/*
//人体モデルの追加
Body body = new Body();
//body.loadBodyObj( "D:/work/洋裁/3Dbody/test.obj" );
body.loadBodyObj( "D:/work/洋裁/3Dbody/test_J150新スキャナー.obj" );
body.scale(body.cpoint, 0.01);
body.remakeMeshView();
 for( int i=0;i< body.meshview.length;i++ ){
  this.stage.group.getChildren().add( body.meshview[i] );
 }
*/





 CollisionPEF c = new CollisionPEF();
 Triangle3D t = new Triangle3D( new ThreeDimension(1,1,-10) , new ThreeDimension(0,1,2) , new ThreeDimension(1,0,1) );
 Square3D v = new Square3D(  new ThreeDimension(1.5,1.5,1.5) , new ThreeDimension(-1.5,-1.5,-1.5) );
 System.out.println( "干渉" + c.isCollisionPEF(t, v) );



return;
}


}
