package lt.esdc.designpatterns.visitor;

public class ReportVisitor implements OrderVisitor {

    private final StringBuilder report = new StringBuilder();

    @Override
    public void visit(Order order) {
        report.append(order.isSuccess() ? "✔ " : "✘ ")
                .append(order.getDescription())
                .append("\n");
    }

    public String getReport() {
        return report.toString();
    }
}
