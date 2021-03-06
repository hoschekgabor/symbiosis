//
// Copyright (C) 2006 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//

package gov.nasa.jpf.jvm.bytecode;

import gov.nasa.jpf.jvm.KernelState;
import gov.nasa.jpf.jvm.MethodInfo;
import gov.nasa.jpf.jvm.SystemState;
import gov.nasa.jpf.jvm.ThreadInfo;

/**
 * this is an artificial instruction that is automatically prepended to
 * a run()/main() method call.
 * 
 * The main purpose is to have a special instruction marking the beginning
 * of a new thread execution which does not cause CGs or is otherwise subject
 * to execution semantics that change the program state.
 * 
 * For instance, without it we would have to add a new ThreadInfo state to
 * determine if the first instruction within this thread was re-executed or
 * just happens to be the first transition we execute within this thread
 * 
 */
public class RUNSTART extends Instruction {

  public RUNSTART (MethodInfo runMth) {
    this.mi = runMth;
    this.insnIndex = -1;
    this.position = -1;
  }

  public Instruction execute (SystemState ss, KernelState ks, ThreadInfo ti) {
    // this insn is never stored in any MethodInfo
    return mi.getInstruction(0);
  }


  public static final int OPCODE = 257;

  public int getByteCode () {
    return OPCODE;
  }

  public boolean isExtendedInstruction() {
    return true;
  }

  public void accept(InstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }

}
