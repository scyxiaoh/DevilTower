package entity;

public class KeyElement extends UndirectedEntity{


	protected KeyElementType type;
	protected String name;
	
	public KeyElement(UndirectedAnimation a, KeyElementType t, String n) {
		super(a);
		this.type = t;
		this.name = n;
	}
}
