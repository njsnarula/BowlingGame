package com.bowl.game;

import java.util.Optional;

public class TengthFrame extends Frame {
	private Integer thirdRollPinCount;

	public Integer getThirdRollPinCount() {
		return thirdRollPinCount;
	}

	public void setThirdRollPinCount(Integer thirdRollPinCount) {
		this.thirdRollPinCount = thirdRollPinCount;
	}	
	
	@Override
	public void calculateBonus() {
		if(this.isStrike()) {
			this.setBonusScore(Optional.ofNullable(this.getSecondRollPinCount()).orElse(0)+Optional.ofNullable(this.getThirdRollPinCount()).orElse(0));
		} else if(this.isSpare()) {
			this.setBonusScore(Optional.ofNullable(this.getThirdRollPinCount()).orElse(0));
		} else {
			this.setBonusScore(0);
		}
	}
	
	
	@Override
	public void calculateTotalKnockedPinsInFrame() {
		if(this.isStrike() || this.isSpare() ) {
				this.setTotalKnockedPins(10);
		} else {
			this.setTotalKnockedPins(this.getFirstRollPinCount() + Optional.ofNullable(this.getSecondRollPinCount()).orElse(0));
		}
	}
}
