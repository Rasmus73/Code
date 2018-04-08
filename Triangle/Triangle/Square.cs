using System.Collections.Generic;

namespace Triangle
{
    public class Square : Shape
    {
        public Square(IReadOnlyList<Line> line) : base(line, new SquareGeometryResolver())
        {
        }
    }
}
