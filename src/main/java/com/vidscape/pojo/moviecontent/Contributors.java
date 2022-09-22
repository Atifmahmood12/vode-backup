package com.vidscape.pojo.moviecontent;

import com.vidscape.pojo.common.Translation;

public class Contributors {
	
	private String contribution;

    private Translation sortableName;

    private Translation firstName;

    private Translation lastName;

    private Translation fullName;

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public Translation getSortableName() {
		return sortableName;
	}

	public void setSortableName(Translation sortableName) {
		this.sortableName = sortableName;
	}

	public Translation getFirstName() {
		return firstName;
	}

	public void setFirstName(Translation firstName) {
		this.firstName = firstName;
	}

	public Translation getLastName() {
		return lastName;
	}

	public void setLastName(Translation lastName) {
		this.lastName = lastName;
	}

	public Translation getFullName() {
		return fullName;
	}

	public void setFullName(Translation fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Contributors [contribution=" + contribution + ", sortableName=" + sortableName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", fullName=" + fullName + "]";
	}
    

}
