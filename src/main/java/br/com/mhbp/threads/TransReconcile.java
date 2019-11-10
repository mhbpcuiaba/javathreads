package br.com.mhbp.threads;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransReconcile {

    static Stream<Pending> reconcile(Stream<Pending> pending, Stream<Stream<Processed>> processed) {

        if (pending == null || processed == null || pending.count() == 0 || processed.count() == 0) {
            return Stream.empty();
        }

        List<Pending> pendingTransactionList = pending.collect(Collectors.toList());
        Set<Long> pendingIds = pendingTransactionList.stream().map(Pending::getId).collect(Collectors.toSet());
        // extract nested stream
        Stream<Processed> processedTransactionStream = processed.flatMap(p -> p);
        List<Processed> validProcessedTransactions = processedTransactionStream.filter(pt -> pendingIds.contains(pt.getId()))
                        .collect(Collectors.toList());

        Stream<Processed> validProcessedTransactionsFinal = validProcessedTransactions.stream().filter(p -> p.getStatus() == "DONE" && p.getId() != null);

        return pendingTransactionList.stream().filter(p -> validProcessedTransactionsFinal.anyMatch(pr -> pr.equals(p.getId())));
    }

}

class Pending {

    public long id;

    public long getId() {
        return id;
    }
}

class Processed {
    public String id;
    public String status;
    public String getId() {

        return id;
    }

    public String getStatus() {

        return status;
    }
}