package fx3d;

import tomojavalib.mesh.TwoDimension;

/**3�����̗����̂𓊉e����2�����̎l�p�`������ * */
public class Square2D {
/**8�̒��_�̂����A�ő�ʒu�ɂ���_*/
public TwoDimension max;
/**8�̒��_�̂����A�ő�ʒu�ɂ���_*/
public TwoDimension min;

/**�R���X�g���N�^*/
public Square2D( TwoDimension max , TwoDimension min ) {
this.max = max;
this.min = min;
}

/**�l�p�`���\������4�_��Ԃ�*/
public TwoDimension[] getFourPoints() {
TwoDimension[] rp= new TwoDimension[4];
rp[0] = new TwoDimension( this.min.x , this.min.y );
rp[1] = new TwoDimension( this.max.x , this.min.y );
rp[2] = new TwoDimension( this.min.x , this.max.y );
rp[3] = new TwoDimension( this.max.x , this.max.y );
return rp;
}


}
