package dev.arsystem.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	
	public Integer getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public Double getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	public Integer getCantidadStock() {
		return cantidadStock;
	}
	
	public void setCantidadStock(Integer cantidadStock) {
		this.cantidadStock = cantidadStock;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<ComprasProducto> getComprasProducto() {
		return comprasProducto;
	}
	
	public void setComprasProducto(List<ComprasProducto> comprasProducto) {
		this.comprasProducto = comprasProducto;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer idProducto;
	
	private String nombre;
	
	@Column(name = "id_categoria")
	private Integer idCategoria;
	
	@Column(name = "codigo_barras")
	private String codigoBarras;
	
	@Column(name = "precio_venta")
	private Double precioVenta;
	
	@Column(name = "cantidad_stock")
	private Integer cantidadStock;
	
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", insertable = false, updatable = false)
	private Categoria categoria;
	
	@OneToMany(mappedBy = "producto")
	private List<ComprasProducto> comprasProducto;
}
