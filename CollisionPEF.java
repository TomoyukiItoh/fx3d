package fx3d;

import tomojavalib.mesh.CPoint;
import tomojavalib.mesh.CTriangle;
import tomojavalib.mesh.ThreeDExe;
import tomojavalib.mesh.ThreeDimension;
import tomojavalib.mesh.TwoDimension;
import tomojavalib.p2cad.Line;
import tomojavalib.p2cad.Point;
import tomojavalib.p2cad.Tmath;

/**Square3D��Triangle3D�̊��Ɋւ��郁�\�b�h�����߂�class*/
public class CollisionPEF {
Tmath t = null;

public CollisionPEF() {
t= new Tmath();
}

/*���쎎��*
public static void main( String[] q ) {
 System.out.println("start");
 Collision c = new Collision();
 Triangle3D t = new Triangle3D( new ThreeDimension(-0,0,0) , new ThreeDimension(10,0,0) , new ThreeDimension(0,10,0) );
 Square3D v = new Square3D( new ThreeDimension(10,10,10) , new ThreeDimension(-10,-10,-10)  );
 System.out.println( "����" + c.isCollisionPEF(t, v) );
}
*/

/**
 * pef�@�ɂ�銱���胁�\�b�h
 * @param t   �O�p�`
 * @param v�@�{�N�Z��
 * @return rb true:������@false �Ȃ�
 */
public boolean isCollisionPEF( Triangle3D t , Square3D v ) {
boolean rb = false;
Square3D tv = t.getAABB();
 System.out.println("1");
//2�̗����̂������Ă��邩
if( this.isCollisionSquareSquare3(tv, v)) {
 System.out.println("2");
 //X2D�O�p�`�̂R�ӂ��ׂĂɂ����āA�Ӗ@��Enor*�̕����ɉ����čł��O����2D�����`�̒��_���ӂ̓����ɓ���B
 //2D�O�p�`�̂R�ӂ��ׂĂɂ����āA�l�p�`��4�̒��_�̂������Ȃ��Ƃ�1��3�p�`�̓����ɓ����Ă��邱��
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
		//3D�O�p�`�̖@�������`�F�b�N
         if( collisionFrontBack( t , v ) ) {
           rb = true;
}}}}}
return rb;
}




/**
 * �����̂�3D�O�p�`�̖ʂɎh�����Ă��邩�̊m�F
 * �����̂�8�̒��_�ɂ��āA���ꂼ��O�p�`�ʂ̗������\�����𒲂ׂ�
 * ���ƕ\��1�ȏ゠��Ύh�����Ă��锻��
 * @param t�@3D�O�p�`
 * @param v�@������
 * @return
 */
private boolean collisionFrontBack( Triangle3D t , Square3D v ) {
boolean rb= false;
ThreeDExe tde = new ThreeDExe();
  //�O�p�`�̕ϊ�
  CTriangle ct = this.convTriangle3DtoCTriangle(t);
  //�����̂�8�̒��_���Ƃ�o��
  ThreeDimension[] eightpoints = v.getEightPoints();
  int ura = 0; int omote =0;
  //8���_�ɂ��ĕ\�����m�F
   for( int i = 0 ; i < eightpoints.length ; i++ ) {
   //System.out.println(eightpoints[i].x);
    double r = tde.getUraOmote( ct , eightpoints[i] );
    if( r > 0 ) { omote++; }else { ura++; }
   }
   //�\�����z���画��
   if( ura>0 ) { if(omote>0) { rb = true; } }
tde=null;
return rb;
}




/**�O�p�`�̌`����ϊ�����*/
private CTriangle convTriangle3DtoCTriangle( Triangle3D t ) {
CTriangle rt = null;
rt = new CTriangle( new CPoint( t.p[0], 0 ) ,new CPoint( t.p[1], 1 ) ,new CPoint( t.p[2], 2 ) , 0 );
return rt;
}



/**
 * �O�p�`��1�ӂɑ΂���l�p�`�_�n���Ȃ��Ƃ�1���O�p�`�̓����ɂ��邱��
 * �O�p�`�̂��ׂĂ̕ӂɓ��Ă͂܂��true
 * @param v�@�l�p�`
 * @param t�@�O�p�`
 * @return
 */
private boolean isInside( Square2D v , Triangle2D t ) {
boolean rb = false;
//Tmath tm = new Tmath();
//2D�l�p�`��4�̒��_�����߂�
Point[] vp = this.getPointsFromSquare2D( v );
int tpl = t.p.length;
int flug =0;
//�O�ӂɂ��ă��[�v
for( int i=0;i<tpl;i++) {
 //�����쐬
 Point p0  = new Point( t.p[i].x , t.p[i].y );
 int ii=i+1;
 if( ii >= tpl ) { ii = ii-tpl; }
 Point p1  = new Point( t.p[ii].x , t.p[ii].y );
 Line l = new Line( p0 , p1 );
 //�O�p���1�ӂƎc�����_�̕����𓾂�
 int ix=i+2;
 if( ix >= t.p.length ) { ix = ix-t.p.length; }
 Point p2  = new Point( t.p[ix].x , t.p[ix].y );
  int houkou1 = 10;
  try { houkou1 = this.t.lineTenHoukou( l , p2 ); }catch( Exception e) {}
 //�O�p���1�ӂƎl�p�e�_�̕����𓾂�
  for(int iii=0;iii<vp.length;iii++) {
    int houkou2 = 11;
    try { houkou2 = this.t.lineTenHoukou( l , vp[iii] ); }catch( Exception e) {}
    System.out.println( "����" + houkou1 +" "+ houkou2);
     if(houkou1==houkou2) { flug++; break;}
  }
 //�����̓_�̕������`�F�b�N
 //if(houkou1==houkou2) { flug++; }
}
//���ׂĂ̕ӂœ����Ȃ�true
System.out.println( "���v" + flug );
if( flug ==3 ) { rb = true; }
return rb;
}



/**
 * �O�p�`��1�ӂɑ΂���l�p�`�̍ŉ��_���O�p�`�̓����ɂ��邱�Ƃ�
 * �O�p�`�̂��ׂĂ̕ӂɓ��Ă͂܂��true
 * @param v�@�l�p�`
 * @param t�@�O�p�`
 * @return
 */
private boolean isInside_old( Square2D v , Triangle2D t ) {
boolean rb = false;
//Tmath tm = new Tmath();
Point[] vp = this.getPointsFromSquare2D( v );
int tpl = t.p.length;
int flug =0;
//�O�ӂɂ��ă��[�v
for( int i=0;i<tpl;i++) {
 //�����쐬
 Point p0  = new Point( t.p[i].x , t.p[i].y );
 int ii=i+1;
 if( ii >= tpl ) { ii = ii-tpl; }
 Point p1  = new Point( t.p[ii].x , t.p[ii].y );
 Line l = new Line( p0 , p1 );
 //������������Ƃ������_�𓾂�
 Point pf = this.getFarPoint(  vp , l );
 //�O�p���1�ӂƎc�����_�̕����𓾂�
 int ix=i+2;
 if( ix >= t.p.length ) { ix = ix-t.p.length; }
 Point p2  = new Point( t.p[ix].x , t.p[ix].y );
  int houkou1 = 10;
  try { houkou1 = this.t.lineTenHoukou( l , p2 ); }catch( Exception e) {}
 //�O�p���1�ӂƎl�p�̍ŉ��_�̕����𓾂�
 int houkou2 = 11;
 try { houkou2 = this.t.lineTenHoukou( l , pf ); }catch( Exception e) {}
 System.out.println( "����" + houkou1 +" "+ houkou2);
 //�����̓_�̕������`�F�b�N
 if(houkou1==houkou2) { flug++; }
}
//���ׂĂ̕ӂœ����Ȃ�true
System.out.println( "���v" + flug );
if( flug ==3 ) { rb = true; }
return rb;
}

/**�_�z�񂩂�ł������_��T���ĕԂ�*/
private Point getFarPoint( Point[] vp , Line l ) {
Point rp = null;
double length = -100;
 for(int i=0;i<vp.length;i++) {
  double vlength = t.senTenKyori(l, vp[i]);
   System.out.println( " " + vlength );
  if( vlength>length ) { rp = vp[i] ; length = vlength ; }
 }
 System.out.println( l.sp.x + " " + l.sp.y + " " + l.ep.x + " " + l.ep.y );
 System.out.println( "�ŉ��_����" + length );
 rp = rp.clone();
return rp;
}



/**2D�l�p�`��4�̓_��Ԃ�*/
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
 * �_p1�Ɠ_p2������l�Ɠ��������ɂ��邩
 * @param p1
 * @param p2
 * @param l
 * @return �����Ȃ�TRUE
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
 *2��3D�����̂������Ă����true��Ԃ�
 * @param q1 1�ڂ̗�����
 * @param q2 2�ڂ̗�����
 * @return�@�����Ă����true
 */
private boolean isCollisionSquareSquare3( Square3D q1 , Square3D q2 ) {
boolean rb = false;
//3�̕��ʂɓ��e�����l�p�`���S�Ċ����Ă���΁A�����Ă���
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
 *2��2D�l�p�`�������Ă����true��Ԃ�
 * @param s1 1�ڂ̎l�p
 * @param s2 2�ڂ̎l�p
 * @return�@�����Ă����true
 */
private boolean isCollisionSquareSquare2( Square2D s1 , Square2D s2 ) {
boolean rb = false;
//2�̎O�p�`�̂������������ɂ��čő�ŏ��̊֌W���m�F����
/*if( s1.min.x <= s2.max.x) {if( s2.min.x <= s1.max.x) {
 if( s1.min.y <= s2.max.y) {if( s2.min.y <= s1.max.y) {
  rb = true;
}}}}*/
if(  ( s1.min.x - s2.max.x ) * ( s1.max.x - s2.min.x  ) <= 0.  ) {
if(  ( s1.min.y - s2.max.y ) * ( s1.max.y - s2.min.y  ) <= 0.  ) {
 rb= true;
}}
System.out.println("�l�p�`����"+rb);
System.out.println("s1"+s1.max.x +" "+s1.max.y+" "+s1.min.x +" "+s1.min.y);
System.out.println("s2"+s2.max.x +" "+s2.max.y+" "+s2.min.x +" "+s2.min.y);
return rb;
}


}
