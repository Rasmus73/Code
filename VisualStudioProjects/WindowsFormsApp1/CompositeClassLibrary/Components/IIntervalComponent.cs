using Model.ValueTypes;

namespace IntervalDisplayLibrary.Components
{
    internal interface IIntervalComponent
    {
        IntervalType Interval { get; }
    }
}