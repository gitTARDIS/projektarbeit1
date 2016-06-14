//shamelessly stolen from https://stendhalgame.org/wiki/Marauroa_Chat_Tutorial/Server
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

import marauroa.common.crypto.Hash;
import marauroa.common.game.AccountResult;
import marauroa.common.game.CharacterResult;
import marauroa.common.game.IRPZone;
import marauroa.common.game.RPAction;
import marauroa.common.game.RPObject;
import marauroa.common.game.Result;
import marauroa.server.game.db.DAORegister;
import marauroa.server.game.db.AccountDAO;
import marauroa.server.game.db.CharacterDAO;
import marauroa.server.db.TransactionPool;
import marauroa.server.db.DBTransaction;
import marauroa.server.game.rp.IRPRuleProcessor;
import marauroa.server.game.rp.RPServerManager;


public class Rule implements IRPRuleProcessor {
  private static Rule instance;

  private World world = World.get();

  private RPServerManager manager;

  public static IRPRuleProcessor get() {
    if (instance == null) {
      instance = new Rule();
    }
    return instance;
  }


  public void setContext(RPServerManager rpman) {
    manager = rpman;
  }

  public boolean checkGameVersion(String game, String version) {
    return game.equals("Chat");
  }

  public synchronized void onTimeout(RPObject character) {
    onExit(character);
  }

  public synchronized boolean onExit(RPObject character) {
    world.remove(character.getID());
    return true;
  }

  public synchronized boolean onInit(RPObject character) {
    IRPZone zone = world.getRPZone(new IRPZone.ID("lobby"));
    zone.add(character);
    return true;
  }

  public synchronized void beginTurn() {
	  //dummy
  }

  public boolean onActionAdd(RPObject caster, RPAction action, List<RPAction> actionList) {
    return true;
  }

  public synchronized void endTurn() {
	  //dummy
  }

  public void execute(RPObject caster, RPAction action) {
    if (action.get("type").equals("chat")) {
      RPObject chatEntry = new RPObject();
      chatEntry.put("text", action.get("text"));
      chatEntry.put("from", caster.get("nick"));
      chatEntry.put("turn", manager.getTurn());
      IRPZone zone = world.getRPZone(new IRPZone.ID(caster.getID().getZoneID()));
      zone.assignRPObjectID(chatEntry);
      zone.add(chatEntry);
    }
  }

  public AccountResult createAccount(String username, String password, String email) {
    TransactionPool transactionPool = TransactionPool.get();
    DBTransaction trans = transactionPool.beginWork();
    AccountDAO accountDAO = DAORegister.get().get(AccountDAO.class);
    try {
      if (accountDAO.hasPlayer(trans, username)) {
        return new AccountResult(Result.FAILED_PLAYER_EXISTS, username);
      }
      accountDAO.addPlayer(trans, username, Hash.hash(password), email);
      transactionPool.commit(trans);
      return new AccountResult(Result.OK_CREATED, username);
    } catch (SQLException e1) {
      transactionPool.rollback(trans);

      return new AccountResult(Result.FAILED_EXCEPTION, username);
    }
  }

  public CharacterResult createCharacter(String username, String characterName, RPObject template) {
    TransactionPool transactionPool = TransactionPool.get();
    DBTransaction trans = transactionPool.beginWork();
    CharacterDAO characterDAO = DAORegister.get().get(CharacterDAO.class);
    try {
      if (characterDAO.hasCharacter(trans, username, characterName)) {
        return new CharacterResult(Result.FAILED_CHARACTER_EXISTS, characterName, template);
      }
      IRPZone zone = world.getRPZone(new IRPZone.ID("lobby"));
      RPObject character = new RPObject(template);
      character.put("nick", characterName);
      zone.assignRPObjectID(character);
      characterDAO.addCharacter(trans, username, characterName, character);
      transactionPool.commit(trans);
      return new CharacterResult(Result.OK_CREATED, characterName, character);
    } catch (Exception e1) {
      transactionPool.rollback(trans);

      return new CharacterResult(Result.FAILED_EXCEPTION, characterName, template);
    }
  }

public String getMimeTypeForResource(String arg0) {
  // TODO Auto-generated method stub
  return null;
}


public InputStream getResource(String arg0) {
  // TODO Auto-generated method stub
  return null;
}
}