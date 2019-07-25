package fx3d;

/**3つの3D座標を含んだ単純な三角形
 * Objファイル読み込み用に作成*/
public class Triangle3D {
public ThreeDimension[] d = null;

public Triangle3D( ThreeDimension x ,ThreeDimension y ,ThreeDimension z ) {
d = new ThreeDimension[3];
d[0] = x;
d[1] = y;
d[2] = z;
}

}
