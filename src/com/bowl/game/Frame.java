package com.bowl.game;

import java.util.Optional;

public class Frame {
	
	private Integer firstRollPinCount;
	private Integer secondRollPinCount;
	private boolean isStrike;
	private boolean isSpare;
	private Frame nextFrame;
	private Frame previousFrame;
	private Integer totalKnockedPins;
	private Integer bonusScore;
	private Integer totalScore;
	public Integer getFirstRollPinCount() {
		return firstRollPinCount;
	}
	public void setFirstRollPinCount(Integer firstRollPinCount) {
		this.firstRollPinCount = firstRollPinCount;
	}
	public Integer getSecondRollPinCount() {
		return secondRollPinCount;
	}
	public void setSecondRollPinCount(Integer secondRollPinCount) {
		this.secondRollPinCount = secondRollPinCount;
	}
	public boolean isStrike() {
		return isStrike;
	}
	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}
	public boolean isSpare() {
		return isSpare;
	}
	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}
	public Frame getNextFrame() {
		return nextFrame;
	}
	public void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
	public Frame getPreviousFrame() {
		return previousFrame;
	}
	public void setPreviousFrame(Frame previousFrame) {
		this.previousFrame = previousFrame;
	}
	public Integer getTotalKnockedPins() {
		return totalKnockedPins;
	}
	public void setTotalKnockedPins(Integer totaKnockedPins) {
		this.totalKnockedPins = totaKnockedPins;
	}
	public Integer getBonusScore() {
		return bonusScore;
	}
	public void setBonusScore(Integer bonusScore) {
		this.bonusScore = bonusScore;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	public void calculateBonus() {
		
		if(this.isStrike()) {
			if(this.getNextFrame()!=null) {
				if(this.getNextFrame().isStrike() && this.getNextFrame().getNextFrame()!=null) {
					this.setBonusScore(this.getNextFrame().getFirstRollPinCount()+this.getNextFrame().getNextFrame().getFirstRollPinCount());
				} else {
					this.setBonusScore(this.getNextFrame().getFirstRollPinCount()+this.getNextFrame().getSecondRollPinCount());
			    }	
			}		
		} else if (this.isSpare) {
			if(this.getNextFrame()!=null) { 
				this.setBonusScore(this.getNextFrame().getFirstRollPinCount());
			}
		} else {
			this.setBonusScore(0);
		}			
		
	}
	
	
	public void calculateTotalKnockedPinsInFrame() {		
			this.setTotalKnockedPins(this.getFirstRollPinCount() + Optional.ofNullable(this.getSecondRollPinCount()).orElse(0));
	}
	
	

}
