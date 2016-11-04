/**
 * 2013-7-21 下午2:40:20
 */
package com.xnradmin.po.system;


/**
 * @autohr: bin_liu
 */
public class SysMavenDepend{
    private Integer id;
    
    private String modelName;
    
    private Integer outType;
    
    private String groupId;
    
    private String artifactId;
    
    private String version;
    
    private String parentArtifactId;
    
    private String parentGroupId;
    
    private String parentVersion;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getModelName(){
        return modelName;
    }

    public void setModelName(String modelName){
        this.modelName = modelName;
    }

    public Integer getOutType(){
        return outType;
    }

    public void setOutType(Integer outType){
        this.outType = outType;
    }

    public String getGroupId(){
        return groupId;
    }

    public void setGroupId(String groupId){
        this.groupId = groupId;
    }

    public String getArtifactId(){
        return artifactId;
    }

    public void setArtifactId(String artifactId){
        this.artifactId = artifactId;
    }

    public String getVersion(){
        return version;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public String getParentArtifactId(){
        return parentArtifactId;
    }

    public void setParentArtifactId(String parentArtifactId){
        this.parentArtifactId = parentArtifactId;
    }

    public String getParentGroupId(){
        return parentGroupId;
    }

    public void setParentGroupId(String parentGroupId){
        this.parentGroupId = parentGroupId;
    }

    public String getParentVersion(){
        return parentVersion;
    }

    public void setParentVersion(String parentVersion){
        this.parentVersion = parentVersion;
    }
}
