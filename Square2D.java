package fx3d;

import tomojavalib.mesh.TwoDimension;

/**3次元の立方体を投影した2次元の四角形を示す * */
public class Square2D {
/**8つの頂点のうち、最大位置にある点*/
public TwoDimension max;
/**8つの頂点のうち、最大位置にある点*/
public TwoDimension min;

/**コンストラクタ*/
public Square2D( TwoDimension max , TwoDimension min ) {
this.max = max;
this.min = min;
}

/**四角形を構成する4点を返す*/
public TwoDimension[] getFourPoints() {
TwoDimension[] rp= new TwoDimension[4];
rp[0] = new TwoDimension( this.min.x , this.min.y );
rp[1] = new TwoDimension( this.max.x , this.min.y );
rp[2] = new TwoDimension( this.min.x , this.max.y );
rp[3] = new TwoDimension( this.max.x , this.max.y );
return rp;
}


}
