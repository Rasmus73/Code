using System.Collections.Generic;
using Triangle.Interface;

namespace Triangle
{
    public class SquareGeometryResolver : IGeometryResolver
    {
        public GeometryType GetGeometryType(IReadOnlyList<Line> lines)
        {
            if (lines.Count != 4)
            {
                return GeometryType.UnknownShape;
            }

            if (lines[0].Length == lines[1].Length &&
                lines[1].Length == lines[2].Length &&
                lines[2].Length == lines[3].Length)
                {
                    return GeometryType.Square;
                }
            
            return GeometryType.UnknownShape;
        }
    }
}