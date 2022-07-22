package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Interface which obligates the child classes some methods.
 */
public interface PanelInterface {


  @Override
  boolean equals(final Object o);

  /**
   * This function add a player to the array of players. There be a max of 2 players in a panel.
   * When to players get into a fight,one of them will lose and will be removed from the panel
   *
   * @param player Player to be added
   */
  void addPlayer(Player player);

  /**
   * Removes the specified player from the player array.
   *
   * @param player player to be added
   */
  void removePlayer(Player player);


  /**
   * Method which returns the paths that the players can follow from this panel.
   *
   * @return the paths
   */
  int[] getPaths();

  /**
   * Gives a tuple of the coordinates of the panel.
   *
   * @return an array of the coordinates of the panel.
   */
  int[] getPanelLocation();


  /**
   * Gets the panel type of the panel.
   *
   * @return The type of the panel.
   */
  PanelType getType();

  /**
   * Activates a panel depending on the type.
   *
   * @param player player
   */
  void activatedBy(Player player);
}

