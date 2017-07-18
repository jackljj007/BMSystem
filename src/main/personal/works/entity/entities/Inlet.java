package works.entity.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import works.entity.IdEntity;

@Entity
@Table(name = "inlet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inlet extends IdEntity {

	private String productName;
	private int number;
	private Date inletDate;

	@Column(nullable = false, unique = true)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getInletDate() {
		return inletDate;
	}

	public void setInletDate(Date inletDate) {
		this.inletDate = inletDate;
	}

}
