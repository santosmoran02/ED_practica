package ule.edi.gym;


public interface IGym {
	/**
	 * Devuelve el número total de taquillas del gimnasio. 
	 * @return número total de taquillas.
	 */
	public int getNumberOfLockers();

	
	/**
	 * Calcula el número total de taquillas ocupadas. * * Una taquilla está
	 * ocupada si lockers[i] != null. 
     * @return número de taquillas ocupadas.
	 */
	public int getNumberOfOccupiedLockers();

	/**
	 * Calcula el número de taquillas disponibles. * * Una taquilla está
	 * disponible si lockers[i] == null. 
	 * @return número de taquillas libres.
	 */
	public int getNumberOfAvailableLockers();

	/**
	 * Devuelve el usuario que ocupa la taquilla indicada.
	 * @param pos número de taquilla (empieza en 1). 
	 * @return User almacenado o null si está libre o la posición no es válida.
	 */
	public User getLocker(int pos);

	/**
	 * Libera la taquilla indicada. 
	 * @param pos número de taquilla (empieza en 1). 
	 * @return usuario que ocupaba la taquilla o null si estaba libre.
	 */
	public User releaseLocker(int pos);
	/**
 	* Crea (o marca) una taquilla como NO ACTIVA.
 	*
 	* Una taquilla no activa:
	* - no puede alquilarse
 	* - no cuenta como disponible
 	* - no tiene usuario asignado (user == null)
 	*
 	* Si la taquilla estaba ocupada, la operación NO se realizará.
 	*
 	* @param pos número de taquilla (empieza en 1)
 	* @return true si la taquilla pasa a estado no activo,
 	* false si la posición no es válida o estaba ocupada.
 	*/
	public boolean addInactiveLocker(int pos);
	/**
 	* Calcula el número de taquillas no activas del gimnasio.
 	*
 	* Una taquilla no activa es aquella cuyo atributo active == false.
 	*
 	* @return número total de taquillas fuera de servicio.
	 */
	public int getNumberOfInactiveLockers();

	/**
	 * Alquila una taquilla concreta. 
	 * - lockers[pos-1] debe ser null. 
	 * - Un usuario no puede tener más de una taquilla. 
	 * @param pos número de taquilla. 
	 * @param nif identificador del usuario. 
	 * @param name nombre del usuario. 
	 * @param age edad del usuario. 
	 * @param userType tipo de usuario (BASIC o PREMIUM). 
	 * @return true si la asignación se realiza correctamente; false
	 * en caso contrario.
	 */
	public boolean rentLockerAtPosition(int pos, String nif, String name, int age, UserType userType);

	/**
	 * Asigna automáticamente la primera taquilla disponible. 
	 * @param nif identificador del usuario. 
	 * @param name nombre del usuario. 
	 * @param age edad del usuario. 
	 * @param userType tipo de usuario. 
	 * @return número de taquilla asignada o
	 * -1 si no existe ninguna disponible.
	 */
	public int rentFirstAvailableLocker(String nif, String name, int age, UserType userType);

	/**
	 * Calcula el precio del alquiler de una taquilla.  
	 * - Si el usuario es PREMIUM → precio 0. 
	 * - Si el usuario es BASIC → precio base.
	 * - Si la taquilla está libre → precio 0. 
	 * @param pos posición de la taquilla a calcular 
     * @return precio final del alquiler.
	 */
	public Double calculateLockerPrice(int pos);

	/**
	 * Calcula el precio del alquiler de un usuario.  
	 * - Si el usuario es PREMIUM → precio 0. 
	 * - Si el usuario es BASIC → precio base.
	 * - Si la taquilla está libre → precio 0. 
	 * @param nif nif del usuario a buscar 
     * @return precio final del alquiler.
	 */
	public Double calculateLockerPrice(String nif);

	/**
	 * Calcula el importe total recaudado por el gimnasio en ese momento.
	 * @return suma de precios de todas las taquillas ocupadas por usuarios BASIC.
	 */
	public Double getTotalCollection();

	/** 
	 * Devuelve el precio base del alquiler. 
	 * @return precio estándar. 
	 */
	public double getPrice();
	
	/**
	 * Calcula el número de usuarios BASIC con taquilla cuya edad es menor de 18 años.
	 *
	 * @return número de usuarios BASIC menores de edad.
	 */
	public int getNumberOfBasicUsersUnder18();

	/**
	 * Calcula el número de usuarios PREMIUM con taquilla cuya edad es menor de 18 años.
	 *
	 * @return número de usuarios PREMIUM menores de edad.
	 */
	public int getNumberOfPremiumUsersUnder18();

	/**
	 * Calcula el número de usuarios BASIC con taquilla cuya edad es mayor de 60 años.
	 *
	 * @return número de usuarios BASIC mayores de 60 años.
	 */
	public int getNumberOfBasicUsersOver60();

	/**
	 * Calcula el número de usuarios PREMIUM con taquilla cuya edad es mayor de 60 años.
	 *
	 * @return número de usuarios PREMIUM mayores de 60 años.
	 */
	public int getNumberOfPremiumUsersOver60();

}
