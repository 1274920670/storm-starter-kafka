package storm.bolt;

import java.util.Map;

import org.omg.CORBA.PRIVATE_MEMBER;

import clojure.pprint.column_writer__init;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class PrinterBolt extends BaseRichBolt {

	private OutputCollector _collector;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		_collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		System.err.println(input.toString());
		_collector.emit(input, input.getValues());
//		_collector.ack(input);
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("just one field"));
		
	}

}
