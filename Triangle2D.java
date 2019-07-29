package fx3d;
import tomojavalib.mesh.TwoDimension;

/**3��2D���W���܂�2�����O�p�`
 * Obj�t�@�C���ǂݍ��ݗp�ɍ쐬
 �@Voxsel�v�Z�p�Ɏg�p
     //  P2        P* :Poin*
     //  ���_      E* :Edge*
     //  E1  E0
     //  ��    �_
     //  P0��E2��P1
  */

public class Triangle2D {
public TwoDimension[] p = null;

public Triangle2D( TwoDimension p0 ,TwoDimension p1 ,TwoDimension p2 ) {
p = new TwoDimension[3];
p[0] = p0;
p[1] = p1;
p[2] = p2;
}

/**�G�b�W0��Ԃ�*/
public TwoDimension getEdge0() {
TwoDimension rd = new TwoDimension( p[2].x- p[1].x , p[2].y- p[1].y );
return rd;
}
/**�G�b�W1��Ԃ�*/
public TwoDimension getEdge1() {
TwoDimension rd = new TwoDimension( p[0].x- p[2].x , p[0].y- p[2].y );
return rd;
}
/**�G�b�W1��Ԃ�*/
public TwoDimension getEdge2() {
TwoDimension rd = new TwoDimension( p[1].x- p[0].x , p[1].y- p[0].y );
return rd;
}

/**���̎O�p�`������ŏ��̎l�p�`��Ԃ�*/
public Square2D getAABB() {
 Square2D rs = new Square2D( new TwoDimension( -1000000,-1000000 ) , new TwoDimension( 1000000,1000000 ) );
  for(int i=0;i<p.length;i++) {
  if( p[i].x > rs.max.x ) { rs.max.x = p[i].x ;}
  if( p[i].y > rs.max.y ) { rs.max.y = p[i].y ;}
  if( p[i].x < rs.min.x ) { rs.min.x = p[i].x ;}
  if( p[i].y < rs.min.y ) { rs.min.y = p[i].y ;}
 }
 return rs;
}



}
