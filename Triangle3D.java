package fx3d;

/**3��3D���W���܂񂾒P���ȎO�p�`
 * Obj�t�@�C���ǂݍ��ݗp�ɍ쐬*/
public class Triangle3D {
public ThreeDimension[] d = null;

public Triangle3D( ThreeDimension x ,ThreeDimension y ,ThreeDimension z ) {
d = new ThreeDimension[3];
d[0] = x;
d[1] = y;
d[2] = z;
}

}
