package fx3d;

import tomojavalib.mesh.ThreeDimension;
import tomojavalib.mesh.TwoDimension;

/**3�����̗����̂�����
 * */
public class Square3D{
/**8�̒��_�̂����A�ő�ʒu�ɂ���_*/
public ThreeDimension max;
/**8�̒��_�̂����A�ő�ʒu�ɂ���_*/
public ThreeDimension min;

/**�R���X�g���N�^*/
public Square3D( ThreeDimension max , ThreeDimension min ) {
this.max = max;
this.min = min;
}

/**���ʂ֓��e�����l�p�`��Ԃ�*/
public Square2D getProjXY(){
 Square2D rv = new Square2D( new TwoDimension( this.max.x , this.max.y ) , new TwoDimension( this.min.x , this.min.y ) );
 return rv;
}
/**���ʂ֓��e�����l�p�`��Ԃ�*/
public Square2D getProjYZ(){
 Square2D rv = new Square2D( new TwoDimension( this.max.y , this.max.z ) , new TwoDimension( this.min.y , this.min.z ) );
 return rv;
}
/**���ʂ֓��e�����l�p�`��Ԃ�*/
public Square2D getProjZX(){
 Square2D rv = new Square2D( new TwoDimension( this.max.z , this.max.x ) , new TwoDimension( this.min.z , this.min.x ) );
 return rv;
}

/**�l�p�`���\������4�_��Ԃ�*/
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
