package fx3d;

import tomojavalib.mesh.ThreeDimension;
import tomojavalib.mesh.TwoDimension;

/**3次元の立方体を示す
 * */
public class Square3D{
/**8つの頂点のうち、最大位置にある点*/
public ThreeDimension max;
/**8つの頂点のうち、最大位置にある点*/
public ThreeDimension min;

/**コンストラクタ*/
public Square3D( ThreeDimension max , ThreeDimension min ) {
this.max = max;
this.min = min;
}

/**平面へ投影した四角形を返す*/
public Square2D getProjXY(){
 Square2D rv = new Square2D( new TwoDimension( this.max.x , this.max.y ) , new TwoDimension( this.min.x , this.min.y ) );
 return rv;
}
/**平面へ投影した四角形を返す*/
public Square2D getProjYZ(){
 Square2D rv = new Square2D( new TwoDimension( this.max.y , this.max.z ) , new TwoDimension( this.min.y , this.min.z ) );
 return rv;
}
/**平面へ投影した四角形を返す*/
public Square2D getProjZX(){
 Square2D rv = new Square2D( new TwoDimension( this.max.z , this.max.x ) , new TwoDimension( this.min.z , this.min.x ) );
 return rv;
}

/**四角形を構成する4点を返す*/
public ThreeDimension[] getEightPoints() {
ThreeDimension[] rp= new ThreeDimension[8];
rp[0] = new ThreeDimension( this.min.x , this.min.y , this.min.z );
rp[1] = new ThreeDimension( this.min.x , this.min.y , this.max.z );
rp[2] = new ThreeDimension( this.min.x , this.max.y , this.max.z );
rp[3] = new ThreeDimension( this.min.x , this.max.y , this.min.z );
rp[4] = new ThreeDimension( this.max.x , this.min.y , this.min.z );
rp[5] = new ThreeDimension( this.max.x , this.min.y , this.max.z );
rp[6] = new ThreeDimension( this.max.x , this.max.y , this.max.z );
rp[7] = new ThreeDimension( this.max.x , this.max.y , this.min.z );
return rp;
}

}
