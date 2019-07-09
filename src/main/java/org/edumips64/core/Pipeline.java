package org.edumips64.core;

import org.edumips64.core.is.InstructionInterface;

import java.util.HashMap;
import java.util.Map;

/** A class representing the 5 pipeline stages, containing instructions.
 * Has some convenience methods for dealing with them and making the CPU code
 * a bit simpler.
 */
public class Pipeline {

  /** Pipeline stage*/
  public enum Stage {IF, ID, EX, MEM, WB}

  private Map<Stage, InstructionInterface> stageInstructionMap;

  Pipeline() {
    stageInstructionMap = new HashMap<>();
    clear();
  }

  public boolean isEmptyOrBubble(Stage stage) {
    return isEmpty(stage) || isBubble(stage);
  }

  public boolean isEmpty(Stage stage) {
    return stageInstructionMap.get(stage) == null;
  }

  public boolean isBubble(Stage stage) {
    return !isEmpty(stage) && stageInstructionMap.get(stage).getName().equals(" ");
  }

  public int size() {
    return (int) stageInstructionMap.entrySet().stream()
        .filter(e -> e.getValue() != null)
        .count();
  }

  public Map<Stage, InstructionInterface> getInternalRepresentation() {
    return stageInstructionMap;
  }

  public InstructionInterface get(Stage stage) {
    return stageInstructionMap.get(stage);
  }

  /**
   * Shortcut setters/getters for the stages.
   * Like Map.put(), setters return the previous mapping if any, or null if
   * no mapping was in place.
   */
  public InstructionInterface IF() {
    return get(Stage.IF);
  }

  public InstructionInterface ID() {
    return get(Stage.ID);
  }

  public InstructionInterface EX() {
    return get(Stage.EX);
  }

  public InstructionInterface MEM() {
    return get(Stage.MEM);
  }

  public InstructionInterface WB() {
    return get(Stage.WB);
  }

  public InstructionInterface setIF(InstructionInterface instruction) {
    return stageInstructionMap.put(Stage.IF, instruction);
  }

  public InstructionInterface setID(InstructionInterface instruction) {
    return stageInstructionMap.put(Stage.ID, instruction);
  }

  public InstructionInterface setEX(InstructionInterface instruction) {
    return stageInstructionMap.put(Stage.EX, instruction);
  }

  public InstructionInterface setMEM(InstructionInterface instruction) {
    return stageInstructionMap.put(Stage.MEM, instruction);
  }

  public InstructionInterface setWB(InstructionInterface instruction) {
    return stageInstructionMap.put(Stage.WB, instruction);
  }

  public void clear() {
    stageInstructionMap.put(Stage.IF, null);
    stageInstructionMap.put(Stage.ID, null);
    stageInstructionMap.put(Stage.EX, null);
    stageInstructionMap.put(Stage.MEM, null);
    stageInstructionMap.put(Stage.WB, null);
  }
}
