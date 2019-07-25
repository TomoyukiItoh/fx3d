package fx3d;

import java.util.Vector;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import tomojavalib.util.GetMojiretu;

/**Obj�`����3D�f�[�^��ǂ݂���
 * */
public class ObjReader {
//�ǂݍ��ޓ_�ƎO�p�`�ꎞ�ۊ�
private ThreeDimension[] d =null;
private Triangle3D[][] t =null;
private String[] groupname = null;

/**���쎎���p*/
public static void main( String[] aa ) {
ObjReader o = new ObjReader();
//o.loadObj("D:/work/�m��/3Dbody/2015-1.obj");
o.loadObj("D:/work/�m��/3Dbody/Ma_v20170227sam.obj");
}

/**�R���X�g���N�^*/
public ObjReader() {}

public MeshView[] loadObj( String filename ) {
 MeshView[] mv =null;
//trianglemesh�̍쐬
TriangleMesh[] tm = loadObjt( filename );
//MeshView�̍쐬
mv = this.cleateMeshView( tm );
return mv;
}


public TriangleMesh[] loadObjt( String filename ) {
//triangle3d�̍쐬
Triangle3D[][] t3d = cleateTriangle3D( filename );
//trianglemesh�̍쐬
TriangleMesh[] tm = cleateTriangleMesh( t3d );
return tm;
}


public MeshView[] cleateMeshView( TriangleMesh[] tm ) {
MeshView[] mv =new MeshView[ tm.length ];
  for(int i=0;i<tm.length;i++){
  //System.out.println(i+trianglemesh[0].getNormalElementSize());
   mv[i] = new MeshView( tm[i] );
   mv[i].setId( this.groupname[i] );
  }
return mv;
}


/**FX�p��TriangleMesh���쐬*/
 public TriangleMesh[] cleateTriangleMesh( Triangle3D[][] t3d  )
 {
//�t�@�C����ǂݍ���ŎO�p�`���쐬
//Triangle3D[][] tr = this.cleateTriangle3D( filename );

// ���b�V��
  TriangleMesh[]    tmesh  = new TriangleMesh[t3d.length];
 for(int iii=0;iii<tmesh.length;iii++) {
  tmesh[iii]  = new TriangleMesh();
  // �_���쐬
  float[] points = new float[ t3d[iii].length * 9 ];
  int pi=0;
  for(int i=0;i<t3d[iii].length;i++){
   for(int ii=0;ii<3;ii++){
    points[pi] =(float)t3d[iii][i].d[ii].x;  pi++;
    points[pi] =(float)t3d[iii][i].d[ii].y;  pi++;
    points[pi] =(float)t3d[iii][i].d[ii].z;  pi++;
 }}


//coords�쐬
float[] texCoords = new float[  t3d[iii].length * 6  ];
int ti=0;
for(int i=0;i<t3d[iii].length;i++){
 for(int ii=0;ii<3;ii++){
  texCoords[ti] =(float)(t3d[iii][i].d[ii].x );//* scale)/tx + offx;
  //System.out.print( texCoords[ti] +" ");
  ti++;
  texCoords[ti] =(float)(t3d[iii][i].d[ii].y );//* scale)/ty + offy;
  //System.out.print( texCoords[ti] +" ");
  ti++;
 }
 //System.out.println( " ");
}

//face�쐬
 int[] faces = new int[ (int)(points.length/3.*2) ];
 int fi=0;
  for(int i=0;i<faces.length/2.;i++){
  faces[fi] = i; fi++;
  faces[fi] = i; fi++;
 }
tmesh[iii].getPoints().addAll( points );
tmesh[iii].getTexCoords().addAll( texCoords );
tmesh[iii].getFaces().addAll( faces );
 }

return tmesh;
}

/**�t�@�C����ǂݍ����3�p�`�z���Ԃ�*/
public Triangle3D[][] cleateTriangle3D( String filename ) {

//�t�@�C�����J���ĕK�v�ȏ���ǂݍ���
tomojavalib.util.TextFile tf = new tomojavalib.util.TextFile();
String s = "";
Vector <String> v = new Vector();
Vector <String> f = new Vector();
Vector <String> g = new Vector();
//Vector <Vector<String>> vv = new Vector();
Vector <Vector<String>> ff = new Vector();
//�t�@�C�����J����
try{	tf.inOpen( filename );}catch(Exception e){};
do {
//1�s�ǂݍ���
try {	s = tf.read();}catch(Exception e){};

	//�O���[�v������΃O���[�v�f�[�^���i�[����
if( s.indexOf( "g " )==0 ) {
	 g.add( s );
	 //System.out.println( s );
	  if(f.size()!=0) {
	 //System.out.println( s );
	  ff.add( f );
	 }
	  //v = new Vector<String>() ;
	  f = new Vector<String>() ;
 }

	//System.out.println( s );
	if( s.indexOf("v ")==0 ) { if(v!=null) {v.add( s ); }}
	if( s.indexOf("f ")==0 ) { if(f!=null) {f.add( s ); }}

}while( s.equals("null")==false );
//�t�@�C�������
try {	tf.inClose();}catch(Exception e){};
//�ʃf�[�^�̊i�[
if(f.size()!=0) {	  ff.add( f );	 }

System.out.println( "v.size() " + v.size() + " ff.size() " + ff.size());

//3D���W�z��̍쐬
d = new ThreeDimension[v.size()];
   for(int i=0;i<d.length;i++) {
    d[i] = makeThreeDimension( v.get(i) );
    //System.out.println( d[i].x +" "+ d[i].y +" "+d[i].z );
 }

//�O�p�`�̍쐬
t = new Triangle3D[ff.size()][];
 for(int ii=0;ii<t.length;ii++) {
  f= ff.get(ii);
  t[ii] = new Triangle3D[f.size()];
for(int i=0;i<t[ii].length;i++) {
 t[ii][i] = makeTriangle3D( f.get(i),ii );
}}

//�O���[�v���̍쐬
groupname = new String[ g.size() ];
for(int i=0;i<groupname.length;i++) {
 groupname[i] = g.get(i).replace("g ", "");
}


return t ;
}


/**Obj�t�@�C������1�s
 * v -50.839 -273.650 15499.990
v -51.369 -302.180 15499.980
v 2684.090 -910.029 6325.000
v -3139.690 -1191.360 6349.990
 * ��ThreeDimension�ɓ���ĕԂ�
 * */
private ThreeDimension makeThreeDimension( String s ){
 ThreeDimension d =null;
 GetMojiretu gm = new GetMojiretu();
 String[] tmps = gm.getHairetuFromMojiretu( " " , s ) ;
 d = new ThreeDimension(
    Double.parseDouble( tmps[1] )
  , Double.parseDouble( tmps[2] )
  , Double.parseDouble( tmps[3] )
 );
 return d;
}

/**Obj�t�@�C������1�s
f 42211 42210 42121
f 42211 42121 42212...
 * ��Triangle3D�ɓ���ĕԂ�
 * */
private Triangle3D makeTriangle3D( String s ,int ii){
 Triangle3D t =null;
 GetMojiretu gm = new GetMojiretu();
 String[] tmps = gm.getHairetuFromMojiretu( " " , s ) ;
  if( tmps[1].indexOf("/")>-1 ) { tmps[1] = tmps[1].substring(0,tmps[1].indexOf("/")); }
  if( tmps[2].indexOf("/")>-1 ) { tmps[2] = tmps[2].substring(0,tmps[2].indexOf("/")); }
  if( tmps[3].indexOf("/")>-1 ) { tmps[3] = tmps[3].substring(0,tmps[3].indexOf("/")); }
 t = new Triangle3D(
    d[ Integer.parseInt( tmps[1] )-1 ],
    d[ Integer.parseInt( tmps[2] )-1 ],
    d[ Integer.parseInt( tmps[3] )-1 ]
 );
 return t;
}


}
