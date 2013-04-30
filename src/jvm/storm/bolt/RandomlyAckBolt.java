package storm.bolt;

import java.util.Map;
import java.util.Random;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class RandomlyAckBolt extends BaseRichBolt {

	private OutputCollector _collector;
	private Random _rand;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		_collector = collector;
		_rand = new Random(0);
	}

	@Override
	public void execute(Tuple input) {
		
		_collector.emit(input, input.getValues());
		if( _rand.nextBoolean()) {
			_collector.ack(input);
		}
		
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("just one field"));
	}

}
