package com.xnradmin.po;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.cntinker.util.ReflectHelper;
import com.xnradmin.po.PhoneLocal;

/**
 * PhoneLocal entity.
 */
@Entity
@Table(name = "phone_local")
public class PhoneLocal implements java.io.Serializable{

    // Fields

    private Integer id;

    private String phonehead;

    private String local;

    private String localcode;

    private String type;

    private String typetwo;

    public String toString(){
        String res = "";
        try{
            res = ReflectHelper.makeToString(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    // Constructors

    /** default constructor */
    public PhoneLocal(){
    }

    /** full constructor */
    public PhoneLocal(String phonehead,String local,String localcode,
            String type,String typetwo){
        this.phonehead = phonehead;
        this.local = local;
        this.localcode = localcode;
        this.type = type;
        this.typetwo = typetwo;
    }

    public int hashCode(){
        int _hashCode = 0;

        if(phonehead != null){
            _hashCode = 29 * _hashCode + phonehead.hashCode();
        }

        return _hashCode;
    }

    public boolean equals(Object _other){
        if(_other == null){
            return false;
        }

        if(_other == this){
            return true;
        }

        if(! ( _other instanceof PhoneLocal )){
            return false;
        }
        final PhoneLocal _cast = (PhoneLocal) _other;

        if(phonehead == null ? _cast.phonehead != phonehead : !phonehead
                .equals(_cast.phonehead)){
            return false;
        }

        return true;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "PHONEHEAD", length = 50)
    @Index(name = "idx_phone_local_phonehead")
    public String getPhonehead(){
        return this.phonehead;
    }

    public void setPhonehead(String phonehead){
        this.phonehead = phonehead;
    }

    @Column(name = "LOCAL", length = 50)
    @Index(name = "idx_phone_local_local")
    public String getLocal(){
        return this.local;
    }

    public void setLocal(String local){
        this.local = local;
    }

    @Column(name = "LOCALCODE", length = 50)
    public String getLocalcode(){
        return this.localcode;
    }

    public void setLocalcode(String localcode){
        this.localcode = localcode;
    }

    @Column(name = "TYPE", length = 50)
    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Column(name = "TYPETWO", length = 200)
    public String getTypetwo(){
        return this.typetwo;
    }

    public void setTypetwo(String typetwo){
        this.typetwo = typetwo;
    }

}