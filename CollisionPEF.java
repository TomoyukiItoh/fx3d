package fx3d;

import tomojavalib.mesh.CPoint;
import tomojavalib.mesh.CTriangle;
import tomojavalib.mesh.ThreeDExe;
import tomojavalib.mesh.ThreeDimension;
import tomojavalib.mesh.TwoDimension;
import tomojavalib.p2cad.Line;
import tomojavalib.p2cad.Point;
import tomojavalib.p2cad.Tmath;

/**Square3DとTriangle3Dの干渉に関するメソッドを収めたclass*/
public class CollisionPEF {
Tmath t = null;

public CollisionPEF() {
t= new Tmath();
}

/*動作試験*
public static void main( String[] q ) {
 System.out.println("start");
 Collision c = new Collision();
 Triangle3D t = new Triangle3D( new ThreeDimension(-0,0,0) , new ThreeDimension(10,0,0) , new ThreeDimension(0,10,0) );
 Square3D v = new Square3D( new ThreeDimension(10,10,10) , new ThreeDimension(-10,-10,-10)  );
 System.out.println( "干渉" + c.isCollisionPEF(t, v) );
}
*/

/**
 * pef法による干渉判定メソッド
 * @param t   三角形
 * @param v　ボクセル
 * @return rb true:干渉あり　false なし
 */
public boolean isCollisionPEF( Triangle3D t , Square3D v ) {
boolean rb = false;
Square3D tv = t.getAABB();
 System.out.println("1");
//2つの立方体が干渉しているか
if( this.isCollisionSquareSquare3(tv, v)) {
 System.out.println("2");
 //X2D三角形の３辺すべてにおいて、辺法線Enor*の方向に沿って最も前方の2D長方形の頂点が辺の内側に入る。
 //2D三角形の３辺すべてにおいて、四角形の4つの頂点のうち少なくとも1つが3角形の内側に入っていること
 Square2D v2 = v.getProjXY();
 Triangle2D t2 = t.getProjXY();
  if( this.isInside(v2, t2) ) {
   System.out.println("3");
   v2 = v.getProjYZ();
   t2 = t.getProjYZ();
    if( this.isInside(v2, t2) ) {
     System.out.println("4");
     v2 = v.getProjZX();
     t2 = t.getProjZX();
      if( this.isInside(v2, t2) ) {
       System.out.println("5");
		//3D三角形の法線方向チェック
         if( collisionFrontBack( t , v ) ) {
           rb = true;
}}}}}
return rb;
}




/**
 * 立方体が3D三角形の面に刺さっているかの確認
 * 立方体の8つの頂点について、それぞれ三角形面の裏側か表側かを調べる
 * 裏と表が1以上あれば刺さっている判定
 * @param t　3D三角形
 * @param v　立方体
 * @return
 */
private boolean collisionFrontBack( Triangle3D t , Square3D v ) {
boolean rb= false;
ThreeDExe tde = new ThreeDExe();
  //三角形の変換
  CTriangle ct = this.convTriangle3DtoCTriangle(t);
  //立方体の8つの頂点をとり出す
  ThreeDimension[] eightpoints = v.getEightPoints();
  int ura = 0; int omote =0;
  //8頂点について表裏を確認
   for( int i = 0 ; i < eightpoints.length ; i++ ) {
   //System.out.println(eightpoints[i].x);
    double r = tde.getUraOmote( ct , eightpoints[i] );
    if( r > 0 ) { omote++; }else { ura++; }
   }
   //表裏分布から判定
   if( ura>0 ) { if(omote>0) { rb = true; } }
tde=null;
return rb;
}




/**三角形の形式を変換する*/
private CTriangle convTriangle3DtoCTriangle( Triangle3D t ) {
CTriangle rt = null;
rt = new CTriangle( new CPoint( t.p[0], 0 ) ,new CPoint( t.p[1], 1 ) ,new CPoint( t.p[2], 2 ) , 0 );
return rt;
}



/**
 * 三角形の1辺に対する四角形農地少なくとも1つが三角形の内側にあること
 * 三角形のすべての辺に当てはまればtrue
 * @param v　四角形
 * @param t　三角形
 * @return
 */
private boolean isInside( Square2D v , Triangle2D t ) {
boolean rb = false;
//Tmath tm = new Tmath();
//2D四角形の4つの頂点を求める
Point[] vp = this.getPointsFromSquare2D( v );
int tpl = t.p.length;
int flug =0;
//三辺についてループ
for( int i=0;i<tpl;i++) {
 //直線作成
 Point p0  = new Point( t.p[i].x , t.p[i].y );
 int ii=i+1;
 if( ii >= tpl ) { ii = ii-tpl; }
 Point p1  = new Point( t.p[ii].x , t.p[ii].y );
 Line l = new Line( p0 , p1 );
 //三角上の1辺と残った点の方向を得る
 int ix=i+2;
 if( ix >= t.p.length ) { ix = ix-t.p.length; }
 Point p2  = new Point( t.p[ix].x , t.p[ix].y );
  int houkou1 = 10;
  try { houkou1 = this.t.lineTenHoukou( l , p2 ); }catch( Exception e) {}
 //三角上の1辺と四角各点の方向を得る
  for(int iii=0;iii<vp.length;iii++) {
    int houkou2 = 11;
    try { houkou2 = this.t.lineTenHoukou( l , vp[iii] ); }catch( Exception e) {}
    System.out.println( "方向" + houkou1 +" "+ houkou2);
     if(houkou1==houkou2) { flug++; break;}
  }
 //両方の点の方向をチェック
 //if(houkou1==houkou2) { flug++; }
}
//すべての辺で内側ならtrue
System.out.println( "合計" + flug );
if( flug ==3 ) { rb = true; }
return rb;
}



/**
 * 三角形の1辺に対する四角形の最遠点が三角形の内側にあることが
 * 三角形のすべての辺に当てはまればtrue
 * @param v　四角形
 * @param t　三角形
 * @return
 */
private boolean isInside_old( Square2D v , Triangle2D t ) {
boolean rb = false;
//Tmath tm = new Tmath();
Point[] vp = this.getPointsFromSquare2D( v );
int tpl = t.p.length;
int flug =0;
//三辺についてループ
for( int i=0;i<tpl;i++) {
 //直線作成
 Point p0  = new Point( t.p[i].x , t.p[i].y );
 int ii=i+1;
 if( ii >= tpl ) { ii = ii-tpl; }
 Point p1  = new Point( t.p[ii].x , t.p[ii].y );
 Line l = new Line( p0 , p1 );
 //直線からもっとも遠い点を得る
 Point pf = this.getFarPoint(  vp , l );
 //三角上の1辺と残った点の方向を得る
 int ix=i+2;
 if( ix >= t.p.length ) { ix = ix-t.p.length; }
 Point p2  = new Point( t.p[ix].x , t.p[ix].y );
  int houkou1 = 10;
  try { houkou1 = this.t.lineTenHoukou( l , p2 ); }catch( Exception e) {}
 //三角上の1辺と四角の最遠点の方向を得る
 int houkou2 = 11;
 try { houkou2 = this.t.lineTenHoukou( l , pf ); }catch( Exception e) {}
 System.out.println( "方向" + houkou1 +" "+ houkou2);
 //両方の点の方向をチェック
 if(houkou1==houkou2) { flug++; }
}
//すべての辺で内側ならtrue
System.out.println( "合計" + flug );
if( flug ==3 ) { rb = true; }
return rb;
}

/**点配列から最も遠い点を探して返す*/
private Point getFarPoint( Point[] vp , Line l ) {
Point rp = null;
double length = -100;
 for(int i=0;i<vp.length;i++) {
  double vlength = t.senTenKyori(l, vp[i]);
   System.out.println( " " + vlength );
  if( vlength>length ) { rp = vp[i] ; length = vlength ; }
 }
 System.out.println( l.sp.x + " " + l.sp.y + " " + l.ep.x + " " + l.ep.y );
 System.out.println( "最遠点距離" + length );
 rp = rp.clone();
return rp;
}



/**2D四角形の4つの点を返す*/
private Point[] getPointsFromSquare2D( Square2D v ) {
TwoDimension[] d = v.getFourPoints();
Point[] rp = new Point[ d.length ];
for(int i=0;i<rp.length;i++) {
 rp[i] = new Point( d[i].x , d[i].y);
 //System.out.println( rp[i].x + " " + rp[i].y );
}

return rp;
}



/**
 * 点p1と点p2が直線lと同じ方向にあるか
 * @param p1
 * @param p2
 * @param l
 * @return 同じならTRUE
 */
private boolean isSameside( TwoDimension p1 ,TwoDimension p2 , Line l ) {
boolean rb = false;
Tmath t = new Tmath();
Point p = new Point( p1.x , p1.y );
int p1side =10;
try {
p1side = t.lineTenHoukou( l , p );
}catch( Exception e) {}
p = new Point( p2.x , p2.y );
int p2side =11;
try {
p2side = t.lineTenHoukou( l , p );
}catch( Exception e) {}
t=null;
if( p1side == p2side ) { rb = true; }
return rb;
}


/**
 *2つの3D立方体が干渉していればtrueを返す
 * @param q1 1つ目の立方体
 * @param q2 2つ目の立方体
 * @return　干渉していればtrue
 */
private boolean isCollisionSquareSquare3( Square3D q1 , Square3D q2 ) {
boolean rb = false;
//3つの平面に投影した四角形が全て干渉していれば、干渉している
 System.out.println("6");
if( isCollisionSquareSquare2( q1.getProjXY() , q2.getProjXY() ) ) {
 System.out.println("7");
 if( isCollisionSquareSquare2( q1.getProjYZ() , q2.getProjYZ() ) ) {
  System.out.println("8");
  if( isCollisionSquareSquare2( q1.getProjZX() , q2.getProjZX() ) ) {
   System.out.println("9");
   rb = true;
}}}

return rb;
}

/**
 *2つの2D四角形が干渉していればtrueを返す
 * @param s1 1つ目の四角
 * @param s2 2つ目の四角
 * @return　干渉していればtrue
 */
private boolean isCollisionSquareSquare2( Square2D s1 , Square2D s2 ) {
boolean rb = false;
//2つの三角形のｘ方向ｙ方向について最大最小の関係を確認する
/*if( s1.min.x <= s2.max.x) {if( s2.min.x <= s1.max.x) {
 if( s1.min.y <= s2.max.y) {if( s2.min.y <= s1.max.y) {
  rb = true;
}}}}*/
if(  ( s1.min.x - s2.max.x ) * ( s1.max.x - s2.min.x  ) <= 0.  ) {
if(  ( s1.min.y - s2.max.y ) * ( s1.max.y - s2.min.y  ) <= 0.  ) {
 rb= true;
}}
System.out.println("四角形干渉"+rb);
System.out.println("s1"+s1.max.x +" "+s1.max.y+" "+s1.min.x +" "+s1.min.y);
System.out.println("s2"+s2.max.x +" "+s2.max.y+" "+s2.min.x +" "+s2.min.y);
return rb;
}


}
