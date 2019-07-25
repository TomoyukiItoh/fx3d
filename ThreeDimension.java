package fx3d;

import tomojavalib.p2cad.Youso;

/**xyzç¿ïWÇäiî[Ç∑ÇÈ*/
public class ThreeDimension  implements Cloneable {
public double x;
public double y;
public double z;


public ThreeDimension( double tx , double ty , double tz ){x =tx;y=ty;z=tz;}
public ThreeDimension(){x =0.;y=0.;z=0.;}

public ThreeDimension clone(){
try {
 ThreeDimension td = (ThreeDimension)super.clone();
 td.x = this.x;
 td.y = this.y;
 td.z = this.z;
 return (ThreeDimension) td ;
} catch (CloneNotSupportedException e) {System.out.println(e.toString());return null;/*throw new InternalError(e.toString());*/}
} 


}
