package com.bowl.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game implements GameInterface {
	
	
	private List<Integer> rolls = new ArrayList<Integer>();
	private List<Frame> frames = new ArrayList<Frame>();

	@Override
	public void roll(int noOfPinsKnocked) {
		rolls.add(noOfPinsKnocked);		
	}

	@Override
	public int score() {
		
		boolean isValid = checkAndBuildValidFrameList(rolls);
		
		if (isValid) {
			
			int score=0;
			
			for(Frame f: frames) {
				
				f.calculateTotalKnockedPinsInFrame();
				f.calculateBonus();				
				f.setTotalScore(f.getTotalKnockedPins()+Optional.ofNullable(f.getBonusScore()).orElse(0));				
				score = score+f.getTotalScore();
			}
			
			return score;
			
		} else {
			return -1;
		}
		
	}
	
	
	
	/**
	 * This method is used to validate the input added with rolls method and build a list of 10 Frame Objects 
	 * @param rolls
	 * @return true if input is valid i.e Frames are less than or eqaul to 10 or no frame has total pins knock count greater than 10 (Except for Tength Frame which is special frame)
	 */
	public boolean checkAndBuildValidFrameList(List<Integer> rolls) {
		
		if(rolls.size()>MAX_ROLLS) {
			return false;
		}
		
		int i=0;
		Frame previousFrame =null;
		Frame currentFrame =null;
		while(i<rolls.size()) {
			
			int knockedPins = rolls.get(i);				
			
			if(frames.size()!=MAX_FRAMES-1) {
				if(knockedPins==10 && currentFrame==null) {
					Frame frame	= new Frame();
					frame.setFirstRollPinCount(knockedPins);
					frame.setSecondRollPinCount(null);
					frame.setStrike(true);
					frame.setPreviousFrame(previousFrame);
					if(previousFrame!=null && i!=rolls.size()-1) {
						previousFrame.setNextFrame(frame);
					}
					previousFrame=frame;
					frames.add(frame);
					i++;
				} else if(knockedPins!=10 && currentFrame==null) {
					Frame frame	= new Frame();
					frame.setFirstRollPinCount(knockedPins);
					frame.setPreviousFrame(previousFrame);
					if(previousFrame!=null && i!=rolls.size()-1) {
						previousFrame.setNextFrame(frame);
					}
					currentFrame = frame;
					i++;
				} else if(knockedPins!=10 && currentFrame!=null && currentFrame.getFirstRollPinCount()!=null) {
					int firstRollKnockedPins = currentFrame.getFirstRollPinCount();
					if(firstRollKnockedPins+knockedPins>10) {
						return false;
					} else if (firstRollKnockedPins+knockedPins==10) {
						currentFrame.setSpare(true);
					}
					currentFrame.setSecondRollPinCount(knockedPins);
					frames.add(currentFrame);
					previousFrame=currentFrame;
					currentFrame = null;
					i++;
				} else {
					return false;
				}
			} else if(frames.size()==MAX_FRAMES-1){
				if(knockedPins==10 && currentFrame==null) {
					TengthFrame frame	= new TengthFrame();
					frame.setFirstRollPinCount(knockedPins);
					frame.setSecondRollPinCount(null);
					frame.setThirdRollPinCount(null);
					frame.setStrike(true);
					if(previousFrame!=null && i!=rolls.size()-1) {
						previousFrame.setNextFrame(frame);
					}
					frame.setPreviousFrame(previousFrame);
					currentFrame=frame;
					i++;
				} else if(knockedPins==10 && currentFrame!=null && currentFrame.getFirstRollPinCount()!=null && currentFrame.getSecondRollPinCount()==null && currentFrame.isStrike()) {
					currentFrame.setSecondRollPinCount(knockedPins);
					i++;
				} else if(knockedPins==10 && currentFrame!=null && currentFrame.getSecondRollPinCount()!=null && currentFrame.isStrike()) {
					TengthFrame tengthFrame = (TengthFrame) currentFrame;
					tengthFrame.setThirdRollPinCount(knockedPins);
					frames.add(currentFrame);
					previousFrame=currentFrame;
					currentFrame = null;
					i++;
				} else if(knockedPins!=10 && currentFrame==null) {
					Frame frame	= new Frame();
					frame.setFirstRollPinCount(knockedPins);
					frame.setPreviousFrame(previousFrame);
					if(previousFrame!=null && i!=rolls.size()-1) {
						previousFrame.setNextFrame(frame);
					}
					currentFrame = frame;
					i++;
				} else if(knockedPins!=10 && currentFrame!=null && currentFrame.getFirstRollPinCount()!=null && currentFrame.getSecondRollPinCount()==null) {
					int firstRollKnockedPins = currentFrame.getFirstRollPinCount();
					if(firstRollKnockedPins+knockedPins>10) {
						return false;
					} else if (firstRollKnockedPins+knockedPins==10) {
						currentFrame.setSpare(true);
					}
					frames.add(currentFrame);
					previousFrame=currentFrame;
					currentFrame = null;					
					i++;
				} else if(knockedPins!=10 && currentFrame!=null && currentFrame.getSecondRollPinCount()!=null && currentFrame.isSpare()) {					
					TengthFrame tengthFrame = (TengthFrame) currentFrame;
					tengthFrame.setThirdRollPinCount(knockedPins);
					i++;
				} else {
					return false;
				}
			}
			
		}
		
		if(frames.size()>MAX_FRAMES) {
			return false;
		}
		
		return true;
	}

}
