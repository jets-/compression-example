package ze.duado.compress.service;

public interface Compress {

    String getName();

    byte[] compress(byte[] data);

    byte[] decompress(byte[] data);

    byte[] decompress(byte[] data, Integer value);

}
