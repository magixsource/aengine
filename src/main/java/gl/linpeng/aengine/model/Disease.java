package gl.linpeng.aengine.model;

import javax.persistence.Entity;

import org.beetl.sql.core.mapper.BaseMapper;

@Entity
public class Disease extends AbstractModel{

    private String name;

    public Disease() {
    }

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public interface Mapper extends BaseMapper<Disease> {
    }
}
