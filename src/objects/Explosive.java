package objects;

/** Interface, das von allen Klassen implementiert wird, deren Instanzen bei Loeschung nachfolgend eine Explosion produzieren. Es soll die Moeglichkeit 
 * bestehen ein solches Objekt zu loeschen, ohne eine Explosion zu generieren (silent-Attribut auf true). */

public interface Explosive {

	public void generateExplosion();
	
	public void setSilent(boolean silent);
	
}
