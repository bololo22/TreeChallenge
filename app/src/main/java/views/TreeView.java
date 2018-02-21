package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import com.tradesy.android.tradesytest.R;

import java.util.List;

import model.Node;
import model.Tree;

public class TreeView extends View {

    // dimensions
    float top, right;                           // view top and right bounds in pixels
    float nodeRadius;                           // node radius size in pixels
    float lineStroke;                           // line stroke size in pixels
    float levelHeight;                          // distance between each level of nodes in pixels

    float xposition[] = new float[10];

    // paint
    Paint paint;
    int nodeColor, lineColor, touchedColor;     // UI colors

    boolean init;                               // initialized flag

    Tree tree;

    public TreeView(Context context) {
        super(context);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Sets the tree and invalidates the view to then draw the tree
     *
     * @param tree
     */
    public void setTree(Tree tree) {
        if (tree == null) {
            throw new IllegalStateException("Null tree provided");
        }

        this.tree = tree;
        invalidate();
    }

    /**
     * Recursively draws the tree. Make sure nodes and lines do not cross/intersect. You can use the
     * provided comments as a place to start or write your own function from scratch.
     *
     * Some constraints to simplify the problem:
     * 1. The level of each tree is a fixed distance {@link TreeView#levelHeight}.
     * 2. Do not worry about trees of arbitrary depth, and children counts; the only test inputs are
     * the provided files
     *
     * @param canvas
     *
     * @see Paint#setColor(int)
     * @see Paint#setStrokeWidth(float)
     * @see Canvas#drawLine(float, float, float, float, Paint)
     * @see Canvas#drawCircle(float, float, float, Paint)
     */
    void drawTree(Canvas canvas, Node node, int x) {
        float xdistance = 0;
        float ydistance = 0;

        if(getCurrentDepth(node) == 1){
            xdistance = 25 + xposition[node.getChildren().size()];
        }else if(getCurrentDepth(node) == 2){
            xdistance = 75;
        }

        List<Node> children = node.getChildren();
        if (children != null && !children.isEmpty()) {
            // TODO: Determine amount of space between children
            if(getCurrentDepth(node) == 1){
                xdistance = 25;
            }else if(getCurrentDepth(node) == 2){
                xdistance = 75;
            }

            // TODO: Determine where to draw next level
            ydistance = levelHeight * getCurrentDepth(node);

            // TODO: Draw relationships as lines
            paint.setColor(lineColor);

            // TODO: Determine drawing bounds of children nodes

            // TODO: Draw each child passing along any necessary information

            for (int i = 0; i < children.size(); i++) {
                xposition[i] = (xdistance * x) + nodeRadius;
                drawTree(canvas, children.get(i), i);
            }
        }

        // TODO: Draw node as a circle
        paint.setColor(nodeColor);
        canvas.drawCircle((xdistance * x) + nodeRadius, (getHeight()/2 - nodeRadius) - ydistance, nodeRadius, paint);

    }

    /**
     * Initialization
     */
    void init() {
        init = true;
        top = getTop() + nodeRadius;
        right = getRight();

        nodeRadius = getResources().getDimension(R.dimen.node_radius);
        levelHeight = getResources().getDimension(R.dimen.level_height);
        lineStroke = getResources().getDimension(R.dimen.line_stroke);

        paint = new Paint();
        paint.setAntiAlias(true);

        nodeColor = ResourcesCompat.getColor(getResources(), R.color.blue, null);
        lineColor = ResourcesCompat.getColor(getResources(), R.color.lightBlue, null);
        touchedColor = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!init) init();
        drawTree(canvas, this.tree.getRoot(), 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) return specSize;

        int result = getPaddingLeft() + getPaddingRight();

        return result;
    }

    private int getCurrentDepth(Node root){
        int maxChildLevel = 0;
        for (Node child : root.getChildren()) {
            maxChildLevel = Math.max(maxChildLevel, getCurrentDepth(child));
        }
        return maxChildLevel + 1;
    }
}
