using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Model.ValueTypes;

namespace CompositeClassLibrary.CompositePattern
{
    public abstract class IntervalComponent : IIntervalComponent
    {
        public IntervalType Interval { get; }

        public IntervalComponent(IntervalType interval)
        {
            if (interval == null) throw new ArgumentNullException(nameof(interval));

            Interval = interval;
        }
    }
}