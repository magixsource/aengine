package gl.linpeng.aengine.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.beetl.sql.core.annotatoin.AutoID;

/**
 * Abstract model
 * 
 * @author linpeng
 *
 */
@MappedSuperclass
public abstract class AbstractModel {

	@Id
	private Long id;

	private Date createTime;
	private Date updateTime;

	public AbstractModel() {
		createTime = new Date();
		updateTime = new Date();
	}

	@AutoID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
