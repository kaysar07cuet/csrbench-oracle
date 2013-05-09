package eu.planetdata.srbench.oracle.result;

public class StreamProcessorOutputBuilder {
	public enum S2ROperator {Istream, Rstream, Dstream};

	private S2ROperator operator;
	private boolean outputEmptyRelation;
	private TimestampedRelation previousRelation;

	private StreamProcessorOutput output;

	public StreamProcessorOutputBuilder(S2ROperator operator, boolean outputEmptyRelation) {
		super();
		this.operator = operator;
		this.outputEmptyRelation = outputEmptyRelation;
		output = new StreamProcessorOutput();
	}

	public void addRelation(TimestampedRelation relation){
		if(operator.equals(S2ROperator.Rstream)){
			if(relation.getBindings().size()==0){
				if(outputEmptyRelation){
					relation.addElement(TimestampedRelationElement.createEmptyTimestampedRelationElement(relation.getComputationTimestamp()));
					output.addRelation(relation);
				}
			} else
				output.addRelation(relation);
		} else if(operator.equals(S2ROperator.Istream)){
			if(previousRelation==null){
				if(relation.getBindings().size()==0){
					if(outputEmptyRelation){
						relation.addElement(TimestampedRelationElement.createEmptyTimestampedRelationElement(relation.getComputationTimestamp()));
						output.addRelation(relation);
					}
				} else
					output.addRelation(relation);
			}
			else{
				TimestampedRelation diff = new TimestampedRelation(relation.minus(previousRelation), relation.getComputationTimestamp());
				if(diff.getBindings().size()==0){
					if(outputEmptyRelation){
						diff.addElement(TimestampedRelationElement.createEmptyTimestampedRelationElement(relation.getComputationTimestamp()));
						output.addRelation(diff);
					}
				} else
					output.addRelation(diff);
			}
		} else { //Dstream
			if(previousRelation!=null){
				TimestampedRelation diff = new TimestampedRelation(previousRelation.minus(relation), relation.getComputationTimestamp());
				if(diff.getBindings().size()==0){
					if(outputEmptyRelation){
						diff.addElement(TimestampedRelationElement.createEmptyTimestampedRelationElement(relation.getComputationTimestamp()));
						output.addRelation(diff);
					}
				} else
					output.addRelation(diff);
			}
		}
		previousRelation=relation;
	}

	public StreamProcessorOutput getOutputStreamResult(){
		return output;
	}


}