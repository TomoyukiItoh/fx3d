package fx3d;

import tomojavalib.mesh.ThreeDExe;
import tomojavalib.mesh.ThreeDimension;
import tomojavalib.mesh.TwoDimension;

/**3つの3D座標を含んだな三角形
 * Objファイル読み込み用に作成
 　Voxsel計算用に使用
     //  P2        P* :Poin*
     //  │＼      E* :Edge*
     //  E1  E0
     //  │    ＼
     //  P0─E2─P1
  */
public class Triangle3D {
public ThreeDimension[] p = null;

public Triangle3D( ThreeDimension p0 ,ThreeDimension p1 ,ThreeDimension p2 ) {
p = new ThreeDimension[3];
p[0] = p0;
p[1] = p1;
p[2] = p2;
}

/**エッジ0を返す*/
public ThreeDimension getEdge0() {
ThreeDExe tde = new ThreeDExe();
ThreeDimension rd = tde.minus(p[2], p[1]);
return rd;
}

/**エッジ1を返す*/
public ThreeDimension getEdge1() {
ThreeDExe tde = new ThreeDExe();
ThreeDimension rd = tde.minus(p[0], p[2]);
return rd;
}

/**エッジ1を返す*/
public ThreeDimension getEdge2() {
ThreeDExe tde = new ThreeDExe();
ThreeDimension rd = tde.minus(p[1], p[0]);
return rd;
}

/**3次元三角形を各平面に投影した2次元三角形を得る*/
public Triangle2D getProjXY() {
Triangle2D rt = new Triangle2D(
  new TwoDimension( this.p[0].x , this.p[0].y )
, new TwoDimension( this.p[1].x , this.p[1].y )
, new TwoDimension( this.p[2].x , this.p[2].y ) );
return rt;
}

/**3次元三角形を各平面に投影した2次元三角形を得る*/
public Triangle2D getProjYZ() {
Triangle2D rt = new Triangle2D(
  new TwoDimension( this.p[0].y , this.p[0].z )
, new TwoDimension( this.p[1].y , this.p[1].z )
, new TwoDimension( this.p[2].y , this.p[2].z ) );
return rt;
}

/**3次元三角形を各平面に投影した2次元三角形を得る*/
public Triangle2D getProjZX() {
Triangle2D rt = new Triangle2D(
  new TwoDimension( this.p[0].z , this.p[0].x )
, new TwoDimension( this.p[1].z , this.p[1].x )
, new TwoDimension( this.p[2].z , this.p[2].x ) );
return rt;
}

/**この3D三角形を内包する最小の立方体を返す*/
public Square3D getAABB() {
 Square3D rs = new Square3D( new ThreeDimension( -1000000,-1000000,-1000000 ) , new ThreeDimension( 1000000,1000000,1000000 ) );
  for(int i=0;i<p.length;i++) {
  if( p[i].x > rs.max.x ) { rs.max.x = p[i].x ;}
  if( p[i].y > rs.max.y ) { rs.max.y = p[i].y ;}
  if( p[i].z > rs.max.z ) { rs.max.z = p[i].z ;}
  if( p[i].x < rs.min.x ) { rs.min.x = p[i].x ;}
  if( p[i].y < rs.min.y ) { rs.min.y = p[i].y ;}
  if( p[i].z < rs.min.z ) { rs.min.z = p[i].z ;}
 }
 return rs;
}

//end of class
}
